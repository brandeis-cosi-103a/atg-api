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

    /**
     * Returns a string representation of the card, including its description and id.
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

    public enum Type {
        BUG("Bug", Category.VICTORY, 0, -1),
        METHOD("Method", Category.VICTORY, 2, 1),
        MODULE("Module", Category.VICTORY, 5, 3),
        FRAMEWORK("Framework", Category.VICTORY, 8, 6),
        BITCOIN("Bitcoin", Category.MONEY, 1, 1),
        ETHEREUM("Ethereum", Category.MONEY, 3, 2),
        DOGECOIN("Dogecoin", Category.MONEY, 6, 3),

        /**
         * +1 Action
         * Discard any number of cards, then draw that many.
         */
        BACKLOG("Backlog", Category.ACTION, 2, 0),

        /**
         * +4 Cards
         * +1 Buy
         * Each other player draws a card.
         */
        DAILY_SCRUM("Daily Scrum", Category.ACTION, 5, 0),

        /**
         * +2 Cards
         * +1 Action
         * +2 Money
         *
         * Note that "+2 Money" indicates the player has 2 more money to spend for this turn.
         * This does not imply that any money cards are gained by the player.
         */
        IPO("IPO", Category.ACTION, 5, 0),

        /**
         * +2 Money
         * Each other player discards down to 3 cards in hand.
         *
         * Note that "+2 Money" indicates the player has 2 more money to spend for this turn.
         * This does not imply that any money cards are gained by the player.
         */
        HACK("Hack", Category.ACTION, 4, 0),

        /**
         * +2 Cards
         * When another player plays an Attack card, you may reveal this from your hand to
         * be unaffected by it.
         */
        MONITORING("Monitoring", Category.ACTION, 2, 0),

        /**
         * +1 Card
         * +1 Action
         * +1 Money
         *
         * Discard one card per empty Supply pile.
         */
        TECH_DEBT("Tech Debt", Category.ACTION, 4, 0),

        /**
         * Trash a card from your hand. Gain a card costing up to 2 more than the trashed card.
         */
        REFACTOR("Refactor", Category.ACTION, 4, 0),

        /**
         * You may play an Action card from your hand twice.
         *
         * Playing a Throne Room will result in the player being prompted with a PlayCardDecision
         * to select the action card to play twice.
         */
        PARALLELIZATION("Parallelization", Category.ACTION, 4, 0),

        /**
         * +1 Card
         * +2 Actions
         */
        CODE_REVIEW("Code Review", Category.ACTION, 3, 0),

        /**
         * +2 Cards
         * Each other player gains a Bug.
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