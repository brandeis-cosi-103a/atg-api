package edu.brandeis.cosi.atg.cards;

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
 */
public record Card(Type type, int id) {
    public Card {
        java.util.Objects.requireNonNull(type, "Card type must not be null");
    }

    public Type.Category getCategory() {
        return type.getCategory();
    }

    public int value() {
        return type.getValue();
    }

    public int cardost() {
        return type.getCost();
    }

    public String description() {
        return type.getDescription();
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
         * When this card is played, the engine immediately adds a
         * {@link Card.Type#BUG} to the discard pile of each other player.
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

            private final String name;

            /**
             * Constructs a Category with the specified name.
             *
             * @param name the name of the category
             */
            Category(String name) {
                this.name = java.util.Objects.requireNonNull(name, "Category name must not be null");
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