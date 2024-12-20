package edu.brandeis.cosi.atg.api.cards;

import com.fasterxml.jackson.annotation.JsonCreator;
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
 */
public class Card {
    private Type type;
    private int id;

    /**
     * Constructs a Card with the specified type and id.
     *
     * @param type the type of the card
     * @param id   the id of the card
     */
    @JsonCreator
    public Card(@JsonProperty("type") Type type, @JsonProperty("id") int id) {
        this.type = type;
        this.id = id;
    }

    /**
     * Gets the type of the card.
     *
     * @return the type of the card
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the id of the card.
     *
     * @return the id of the card
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the category of the card.
     *
     * @return the category of the card
     */
    @JsonIgnore
    public Type.Category getCategory() {
        return type.getCategory();
    }

    /**
     * Gets the value of the card.
     *
     * @return the value of the card
     */
    @JsonIgnore
    public int getValue() {
        return type.getValue();
    }

    /**
     * Gets the cost of the card.
     *
     * @return the cost of the card
     */
    @JsonIgnore
    public int getCost() {
        return type.getCost();
    }

    /**
     * Gets the description of the card.
     *
     * @return the description of the card
     */
    @JsonIgnore
    public String getDescription() {
        return type.getDescription();
    }

    /**
     * Returns a string representation of the card, including its description and
     * id.
     */
    @Override
    public String toString() {
        return "[" + getDescription() + "(id: " + id + ")]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card)) {
            return false;
        }
        Card other = (Card) o;
        return type.equals(other.type) && id == other.id;
    }

    @Override
    public int hashCode() {
        return type.hashCode() + id;
    }

    /**
     * Represents the type of a card.
     */
    public enum Type {
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
         * {@link edu.brandeis.cosi.atg.api.GameState.TurnPhase#MONEY MONEY}
         * phase. When played, it grants 1 spendable money for the turn on which it was
         * played.
         */
        BITCOIN("Bitcoin", Category.MONEY, 1, 1),
        /**
         * A money card worth 2 money.
         * <br/>
         * <br/>
         * This card is playable during the
         * {@link edu.brandeis.cosi.atg.api.GameState.TurnPhase#MONEY MONEY}
         * phase. When played, it grants 2 spendable money for the turn on which it was
         * played.
         */
        ETHEREUM("Ethereum", Category.MONEY, 3, 2),
        /**
         * A money card worth 3 money.
         * <br/>
         * <br/>
         * This card is playable during the
         * {@link edu.brandeis.cosi.atg.api.GameState.TurnPhase#MONEY MONEY}
         * phase. When played, it grants 3 spendable money for the turn on which it was
         * played.
         */
        DOGECOIN("Dogecoin", Category.MONEY, 6, 3),

        /**
         * +1 Action; Discard any number of cards, then draw that many
         * <br/>
         * <br/>
         * After playing this card, the player will be prompted with a list of
         * {@link edu.brandeis.cosi.atg.api.decisions.DiscardCardDecision
         * DiscardCardDecisions}, one for each card in their hand. The list of decisions
         * will also contain a
         * {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
         * EndPhaseDecision}. The player can continue to choose DiscardCardDecisions
         * until they wish to stop or run out of cards, at which point they should
         * choose the EndPhaseDecision. After the Discard phase ends, the Engine will
         * draw the number of cards discarded into the player's hand, and prompt the
         * player with the next appropriate set of decisions, based on actions
         * remaining.
         */
        BACKLOG("Backlog", Category.ACTION, 2, 0),

        /**
         * +4 Cards, +1 Buy; Each other player draws a card
         * <br/>
         * <br/>
         * When this card is played, the engine will draw 4 cards into the player's
         * hand. Immediately following that (before any other actions), the engine will
         * draw will draw 1 card into each other player's hands.
         */
        DAILY_SCRUM("Daily Scrum", Category.ACTION, 5, 0),

        /**
         * +2 Cards, +1 Action, +2 Money
         * <br/>
         * <br/>
         * When this card is played, the player immediately earns 2 more spendable money
         * for this turn, has two cards drawn into their hand, and is granted an
         * additional action for this turn.
         *
         * Note that "+2 Money" indicates the player has 2 more money to spend for this
         * turn. This does not imply that any money cards are gained by the player.
         */
        IPO("IPO", Category.ACTION, 5, 0),

        /**
         * +2 Money; Each other player discards down to 3 cards in hand
         * <br/>
         * <br/>
         * When this card is played, the engine will first check to see which players
         * have {@link Card.Type#MONITORING Monitoring} cards, and will prompt them to
         * reveal the cards. After that, the engine prompt each player who hasn't
         * avoided the attack with
         * {@link edu.brandeis.cosi.atg.api.decisions.DiscardCardDecision
         * DiscardCardDecisions} until they have 3 cards in hand. The list of possible
         * decisions offered to the players will not include an
         * {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
         * EndPhaseDecision}, indicating that the discarding is not optional.
         *
         * Note that "+2 Money" indicates the player has 2 more money to spend for this
         * turn. This does not imply that any money cards are gained by the player.
         */
        HACK("Hack", Category.ACTION, 4, 0),

        /**
         * +2 Cards; When another player plays an Attack card, you may reveal this from
         * your hand to be unaffected by the attack.
         * <br/>
         * <br/>
         * The engine implements this by prompting the
         * player with a {@link edu.brandeis.cosi.atg.api.decisions.PlayCardDecision}
         * and an {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision} with
         * the phase set to
         * {@link edu.brandeis.cosi.atg.api.GameState.TurnPhase#REACTION REACTION}. If
         * the player
         * wishes to reveal the card and avoid the attack, they choose the
         * PlayCardDecision and select the Monitoring card. If the player does not wish
         * to reveal the card, they choose the EndPhaseDecision. The card is revealed,
         * but remains in the player's hand, and the player is unaffected by the attack.
         *
         * Attack cards are: {@link Card.Type#HACK} and {@link Card.Type#EVERGREEN_TEST
         * Evergreen Test}
         */
        MONITORING("Monitoring", Category.ACTION, 2, 0),

        /**
         * +1 Card, +1 Action, +1 Money; Discard one card per empty Supply pile
         * <br/>
         * <br/>
         * When this card is played, the player immediately earns 1 more spendable money
         * for this turn, has one card drawn into their hand, and is granted an
         * additional action for this turn. Following that, the player will be prompted
         * {@link edu.brandeis.cosi.atg.api.decisions.DiscardCardDecision
         * DiscardCardDecisions}
         * (with no {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision}
         * possible) until they have discarded one card for each empty supply pile.
         */
        TECH_DEBT("Tech Debt", Category.ACTION, 4, 0),

        /**
         * Trash a card from your hand. Gain a card costing up to 2 more than the
         * trashed card.
         * <br/>
         * <br/>
         * When this card is played, the player will be prompted with
         * {@link edu.brandeis.cosi.atg.api.decisions.TrashCardDecision
         * TrashCardDecisions} for each card in their hand. Trashing is not optional, so
         * the list of decisions will not include an EndPhaseDecision. After the player
         * makes a trash decision, they will be prompted with
         * {@link edu.brandeis.cosi.atg.api.decisions.GainCardDecision
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
         * {@link edu.brandeis.cosi.atg.api.decisions.PlayCardDecision
         * PlayCardDecisions} for every other unplayed action card in their hand. The
         * chosen card will then be executed twice. Executing the chosen card does not
         * use additional actions (beyond the one action used to play the
         * Parallelization card).
         */
        PARALLELIZATION("Parallelization", Category.ACTION, 4, 0),

        /**
         * +1 Card, +2 Actions
         * <br/>
         * <br/>
         * When this card is played, the player immediately draws a card, and is granted
         * two additional actions for this turn.
         */
        CODE_REVIEW("Code Review", Category.ACTION, 3, 0),

        /**
         * +2 Cards; Each other player gains a Bug
         * <br/>
         * <br/>
         * When this card is played, the engine immediately adds a {@link Card.Type#BUG}
         * to each other player's discard pile.
         */
        EVERGREEN_TEST("Evergreen Test", Category.ACTION, 5, 0);

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
            this.description = description;
            this.category = category;
            this.value = value;
            this.cost = cost;
        }

        /**
         * Gets the description of the card type.
         *
         * @return the description of the card type
         */
        public String getDescription() {
            return description;
        }

        /**
         * Gets the category of the card type.
         *
         * @return the category of the card type
         */
        public Category getCategory() {
            return category;
        }

        /**
         * Gets the value of the card type.
         *
         * @return the value of the card type
         */
        public int getValue() {
            return value;
        }

        /**
         * Gets the cost of the card type.
         *
         * @return the cost of the card type
         */
        public int getCost() {
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

            private String name;

            /**
             * Constructs a Category with the specified name.
             *
             * @param name the name of the category
             */
            Category(String name) {
                this.name = name;
            }

            /**
             * Gets the name of the category.
             *
             * @return the name of the category
             */
            public String getName() {
                return name;
            }
        }
    }
}