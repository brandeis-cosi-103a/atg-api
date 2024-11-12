package edu.brandeis.cosi.atg.api.cards;

/**
 * Represents a card in the game.
 */
public class Card {
    private Type type;
    private int id;

    /**
     * Constructs a Card with the specified type and id.
     *
     * @param type the type of the card
     * @param id the id of the card
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

    public enum Type {
        METHOD("Method", Category.VICTORY, 2, 1),
        MODULE("Module", Category.VICTORY, 5, 3),
        FRAMEWORK("Framework", Category.VICTORY, 8, 6),
        BITCOIN("Bitcoin", Category.MONEY, 1, 1),
        ETHEREUM("Ethereum", Category.MONEY, 3, 2),
        DOGECOIN("Dogecoin", Category.MONEY, 6, 3);

        private String description;
        private Category category;
        private int value;
        private int cost;

        /**
         * Constructs a Type with the specified description, category, value, and cost.
         *
         * @param description the description of the card type
         * @param category the category of the card type
         * @param value the value of the card type
         * @param cost the cost of the card type
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
            ACTION("Action"),
            MONEY("Money"),
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