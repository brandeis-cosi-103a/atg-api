package edu.brandeis.cosi.atg.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a card in the game.
 * <br/>
 * <br/>
 * Cards have {@link Type types} that determine their category, value, and cost.
 * They also have ids, which are unique to each card in the game. The id of a
 * card is used to distinguish it from other cards of the same type. Two cards
 * are considered equal if they have the same type and id.
 * <br/>
 * <br/>
 * The three broad categories of cards are:
 * {@link Card.Type.Category#ACTION ACTION},
 * {@link Card.Type.Category#MONEY MONEY}, and
 * {@link Card.Type.Category#VICTORY VICTORY}.
 * Action cards have further, informal subcategories, such as "Attack" (
 * {@link Card.Type#HACK} and {@link Card.Type#EVERGREEN_TEST}), and
 * "Reaction" ({@link Card.Type#MONITORING}).
 *
 * @param type the type of the card
 * @param id   the id of the card
 */
public record Card(@JsonProperty("type") Type type, @JsonProperty("id") int id) {
    /**
     * Compact constructor that validates the card type is not null.
     */
    public Card {
        java.util.Objects.requireNonNull(type, "Card type must not be null");
    }

    /**
     * Gets the category of the card.
     *
     * @return the category of the card
     */
    @JsonIgnore
    public Type.Category category() {
        return type.category();
    }

    /**
     * Gets the value of the card.
     *
     * @return the value of the card
     */
    @JsonIgnore
    public int value() {
        return type.value();
    }

    /**
     * Gets the cost of the card.
     *
     * @return the cost of the card
     */
    @JsonIgnore
    public int cost() {
        return type.cost();
    }

    /**
     * Gets the description of the card.
     *
     * @return the description of the card
     */
    @JsonIgnore
    public String description() {
        return type.description();
    }

    @Override
    public String toString() {
        return "[" + description() + "(id: " + id + ")]";
    }

    /**
     * Represents the type of a card.
     */
    public static enum Type {
        /**
         * A victory card worth -1 automation points.
         * <br/>
         * <br/>
         * This card is never playable, but subtracts 1 automation point when the game
         * ends.
         */
        BUG("Bug", Category.VICTORY, 0, -1),
        /**
         * A victory card worth 1 automation point.
         * <br/>
         * <br/>
         * This card is never playable, but grants 1 automation point when the game
         * ends.
         */
        METHOD("Method", Category.VICTORY, 2, 1),
        /**
         * A victory card worth 3 automation points.
         * <br/>
         * <br/>
         * This card is never playable, but grants 1 automation point when the game
         * ends.
         */
        MODULE("Module", Category.VICTORY, 5, 3),
        /**
         * A victory card worth 6 automation points.
         * <br/>
         * <br/>
         * This card is never playable, but grants 1 automation point when the game
         * ends.
         */
        FRAMEWORK("Framework", Category.VICTORY, 8, 6),
        /**
         * A money card worth 1 money.
         * <br/>
         * <br/>
         * This card is playable during the
         * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#MONEY MONEY}
         * phase. When played, it grants 1 spendable money for the turn on which it was
         * played.
         */
        BITCOIN("Bitcoin", Category.MONEY, 0, 1),
        /**
         * A money card worth 2 money.
         * <br/>
         * <br/>
         * This card is playable during the
         * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#MONEY MONEY}
         * phase. When played, it grants 2 spendable money for the turn on which it was
         * played.
         */
        ETHEREUM("Ethereum", Category.MONEY, 3, 2),
        /**
         * A money card worth 3 money.
         * <br/>
         * <br/>
         * This card is playable during the
         * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#MONEY MONEY}
         * phase. When played, it grants 3 spendable money for the turn on which it was
         * played.
         */
        DOGECOIN("Dogecoin", Category.MONEY, 6, 3),

        /**
         * +1 Action; Discard any number of cards, then draw that many.
         * <br/>
         * <br/>
         * After playing this card, the player will be prompted with a list of
         * {@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision
         * DiscardCardDecisions}, one for each card in their hand. The list of decisions
         * will also contain a
         * {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision
         * EndPhaseDecision}. The player can continue to choose DiscardCardDecisions
         * until they wish to stop or run out of cards, at which point they should
         * choose the EndPhaseDecision. After the Discard phase ends, the Engine will
         * draw the number of cards discarded into the player's hand, and prompt the
         * player with the next appropriate set of decisions, based on actions
         * remaining.
         *
         * @since 2
         */
        BACKLOG("Backlog", Category.ACTION, 2, 0),

        /**
         * +4 Cards, +1 Buy; Each other player draws a card.
         * <br/>
         * <br/>
         * When this card is played, the engine will draw 4 cards into the player's
         * hand. Immediately following that (before any other actions), the engine will
         * draw will draw 1 card into each other player's hands.
         *
         * @since 2
         */
        DAILY_SCRUM("Daily Scrum", Category.ACTION, 5, 0),

        /**
         * +2 Cards, +1 Action, +2 Money.
         * <br/>
         * <br/>
         * When this card is played, the player immediately earns 2 more spendable money
         * for this turn, has two cards drawn into their hand, and is granted an
         * additional action for this turn.
         *
         * Note that "+2 Money" indicates the player has 2 more money to spend for this
         * turn. This does not imply that any money cards are gained by the player.
         *
         * @since 2
         */
        IPO("IPO", Category.ACTION, 5, 0),

        /**
         * (Attack card) +2 Money; Each other player discards down to 3 cards in hand.
         * <br/>
         * <br/>
         * When this card is played, the engine will first check to see which players
         * have {@link Card.Type#MONITORING Monitoring} cards, and will prompt them to
         * reveal the cards. After that, the engine prompt each player who hasn't
         * avoided the attack with
         * {@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision
         * DiscardCardDecisions} until they have 3 cards in hand. The list of possible
         * decisions offered to the players will not include an
         * {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision
         * EndPhaseDecision}, indicating that the discarding is not optional.
         *
         * Note that "+2 Money" indicates the player has 2 more money to spend for this
         * turn. This does not imply that any money cards are gained by the player.
         *
         * @since 2
         */
        HACK("Hack", Category.ACTION, 4, 0),

        /**
         * (Reaction card) +2 Cards; When another player plays an Attack card, you may
         * reveal this from your hand to be unaffected by the attack.
         * <br/>
         * <br/>
         * The engine implements this by prompting the
         * player with a {@link edu.brandeis.cosi.atg.decisions.PlayCardDecision}
         * and an {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision} with
         * the phase set to
         * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#REACTION REACTION}. If
         * the player
         * wishes to reveal the card and avoid the attack, they choose the
         * PlayCardDecision and select the Monitoring card. If the player does not wish
         * to reveal the card, they choose the EndPhaseDecision. The card is revealed,
         * but remains in the player's hand, and the player is unaffected by the attack.
         *
         * Attack cards are: {@link Card.Type#HACK} and {@link Card.Type#EVERGREEN_TEST
         * Evergreen Test}
         *
         * @since 2
         */
        MONITORING("Monitoring", Category.ACTION, 2, 0),

        /**
         * +1 Card, +1 Action, +1 Money; Discard one card per empty Supply pile.
         * <br/>
         * <br/>
         * When this card is played, the player immediately earns 1 more spendable money
         * for this turn, has one card drawn into their hand, and is granted an
         * additional action for this turn. Following that, the player will be prompted
         * {@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision
         * DiscardCardDecisions}
         * (with no {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision}
         * possible) until they have discarded one card for each empty supply pile.
         *
         * @since 2
         */
        TECH_DEBT("Tech Debt", Category.ACTION, 4, 0),

        /**
         * Trash a card from your hand; Gain a card costing up to 2 more than the
         * trashed card.
         * <br/>
         * <br/>
         * Trashing a card removes it from the player's deck entirely.
         * When this card is played, the player will be prompted with
         * {@link edu.brandeis.cosi.atg.decisions.TrashCardDecision
         * TrashCardDecisions} for each card in their hand. Trashing is not optional, so
         * the list of decisions will not include an EndPhaseDecision. After the player
         * makes a trash decision, they will be prompted with
         * {@link edu.brandeis.cosi.atg.decisions.GainCardDecision
         * GainCardDecisions} representing the cards they can possibly gain - every card
         * that costs up to 2 more than the trashed card. Gaining a card is also not
         * optional. Gained cards are placed into the player's discard pile.
         */
        REFACTOR("Refactor", Category.ACTION, 4, 0),

        /**
         * You may play an Action card from your hand twice.
         * <br/>
         * <br/>
         * When this card is played, the player is immediately prompted with
         * {@link edu.brandeis.cosi.atg.decisions.PlayCardDecision
         * PlayCardDecisions} for every other unplayed action card in their hand. The
         * chosen card will then be executed twice. Executing the chosen card does not
         * use additional actions (beyond the one action used to play the
         * Parallelization card).
         *
         * @since 2
         */
        PARALLELIZATION("Parallelization", Category.ACTION, 4, 0),

        /**
         * +1 Card, +2 Actions.
         * <br/>
         * <br/>
         * When this card is played, the player immediately draws a card, and is granted
         * two additional actions for this turn.
         */
        CODE_REVIEW("Code Review", Category.ACTION, 3, 0),

        /**
         * (Attack card) +2 Cards; Each other player gains a Bug.
         * <br/>
         * <br/>
         * When this card is played, the engine will first check to see which players
         * have {@link Card.Type#MONITORING Monitoring} cards, and will prompt them to
         * reveal the cards. After that, the engine immediately adds a
         * {@link Card.Type#BUG} to the discard pile of each player who did not reveal
         * a {@link Card.Type#MONITORING Monitoring} card.
         */
        EVERGREEN_TEST("Evergreen Test", Category.ACTION, 5, 0),

        /**
         * +1 Card, +1 Action, +1 Buy.
         * <br/>
         * <br/>
         * When this card is played, the player draws one card, gains one additional
         * action for this turn, and gains one additional buy for this turn.
         *
         * @since 2
         */
        SPRINT_PLANNING("Sprint Planning", Category.ACTION, 3, 0),

        /**
         * Trash a card from your hand. +1 Card per $1 it costs.
         * <br/>
         * <br/>
         * Trashing a card removes it from the player's deck entirely.
         * When this card is played, the player will be prompted with
         * {@link edu.brandeis.cosi.atg.decisions.TrashCardDecision
         * TrashCardDecisions} for each card in their hand. Trashing is not optional, so
         * the list of decisions will not include an EndPhaseDecision. After the player
         * makes a trash decision, the engine will draw a number of cards equal to the
         * cost of the trashed card into the player's hand.
         *
         * @since 2
         */
        MERGE_CONFLICT("Merge Conflict", Category.ACTION, 2, 0),

        /**
         * (Attack card) +3 Cards; Each other player chooses one: discard 2 cards; or
         * gain a Bug.
         * <br/>
         * <br/>
         * When this card is played, the engine will first check to see which players
         * have {@link Card.Type#MONITORING Monitoring} cards, and will prompt them to
         * reveal the cards. After that, the engine prompts each player who hasn't
         * avoided the attack with a choice: either two
         * {@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision
         * DiscardCardDecisions}, or a
         * {@link edu.brandeis.cosi.atg.decisions.GainCardDecision GainCardDecision} for
         * a {@link Card.Type#BUG Bug}. The player must make one of these choices, but
         * is allowed to choose discard even if they don't have 2 cards in hand.
         *
         * @since 2
         */
        TECHNICAL_DEBT_COLLECTION("Technical Debt Collection", Category.ACTION, 6, 0),

        /**
         * +1 Buy, +$1. This turn, cards cost $1 less (but not less than $0).
         * <br/>
         * <br/>
         * When this card is played, the player gains an additional buy and $1 for this
         * turn. Additionally, all cards cost $1 less for the remainder of
         * this turn (minimum cost is $0). T
         *
         * @since 2
         */
        DEPLOYMENT_PIPELINE("Deployment Pipeline", Category.ACTION, 5, 0),

        /**
         * Choose one: +2 Actions; +$2; +2 Cards.
         * <br/>
         * <br/>
         * When this card is played, the engine prompts the player with two options:
         * either gain 2 additional actions for
         * this turn, gain $2 to spend for this turn, or draw 2 cards. The player must
         * choose one option.
         *
         * @since 2
         */
        UNIT_TEST("Unit Test", Category.ACTION, 3, 0);

        private String description;
        private Category category;
        private int value;
        private int cost;

        /**
         * Constructs a Type with the specified description, category, value, and cost.
         *
         * @param description the description of the card type
         * @param category    the category of the card type
         * @param value       the value of the card type
         * @param cost        the cost of the card type
         */
        Type(String description, Category category, int cost, int value) {
            this.description = java.util.Objects.requireNonNull(description, "Type description must not be null");
            this.category = java.util.Objects.requireNonNull(category, "Type category must not be null");
            this.value = value;
            this.cost = cost;
        }

        /**
         * Gets the description of the card type.
         *
         * @return the description of the card type
         */
        public String description() {
            return description;
        }

        /**
         * Gets the category of the card type.
         *
         * @return the category of the card type
         */
        public Category category() {
            return category;
        }

        /**
         * Gets the value of the card type.
         *
         * @return the value of the card type
         */
        public int value() {
            return value;
        }

        /**
         * Gets the cost of the card type.
         *
         * @return the cost of the card type
         */
        public int cost() {
            return cost;
        }

        /**
         * Represents the category of a card type.
         */
        public enum Category {
            /**
             * A card that can be played during the action phase.
             */
            ACTION("Action"),
            /**
             * A card that grants money to spend for the turn in which it is played.
             */
            MONEY("Money"),
            /**
             * A card that grants automation points when the game ends.
             */
            VICTORY("Victory");

            private final String description;

            /**
             * Constructs a Category with the specified description.
             *
             * @param description the description of the category
             */
            Category(String description) {
                this.description = java.util.Objects.requireNonNull(description,
                        "Category description must not be null");
            }

            /**
             * Gets the name of the category.
             *
             * @return the name of the category
             */
            public String description() {
                return description;
            }
        }
    }
}