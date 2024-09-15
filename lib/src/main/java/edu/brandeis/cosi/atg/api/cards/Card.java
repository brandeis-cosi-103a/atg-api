package edu.brandeis.cosi.atg.api.cards;

public class Card {
    private Type type;
    private int id;

    public Card(Type type, int id) {
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }   

    public Type.Category getCategory() {
        return type.getCategory();
    }

    public int getValue() {
        return type.getValue();
    }

    public int getCost() {
        return type.getCost();
    }

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
        
        public enum Category {
            ACTION("Action"),
            MONEY("Money"),
            VICTORY("Victory");

            private String name;
            Category(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        private String name;
        private Category category;
        private int cost;
        private int value;

        Type(String name, Category category, int cost, int value) {
            this.name = name;
            this.category = category;
            this.cost = cost;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Category getCategory() {
            return category;
        }

        public int getCost() {
            return cost;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return name + 
                "(cost: " + cost + 
                ", category: " + category.getName() + 
                ", value: " + value + ")";
        }
    
    }

}