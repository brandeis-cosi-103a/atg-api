package edu.brandeis.cosi.atg.cards;

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
    public Card(Type type, int id) {
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
    public Type.Category getCategory() {
        return type.getCategory();
    }

    /**
     * Gets the value of the card.
     *
     * @return the value of the card
     */
    public int getValue() {
        return type.getValue();
    }

    /**
     * Gets the cost of the card.
     *
     * @return the cost of the card
     */
    public int getCost() {
        return type.getCost();
    }

    /**
     * Gets the description of the card.
     *
     * @return the description of the card
     */
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
        BITCOIN("Bitcoin", Category.MONEY, 1, 1),
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
        DOGECOIN("Dogecoin", Category.MONEY, 6, 3);

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