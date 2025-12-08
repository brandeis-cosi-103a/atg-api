package edu.brandeis.cosi.atg.decisions;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a decision by a player to choose between different effects.
 * <br/>
 * <br/>
 * This decision type is used when a card offers multiple alternative effects
 * and the player must choose one. Each effect is represented by an explicit
 * enum value that defines both the beneficial and penalty options available
 * in the game.
 * <br/>
 * <br/>
 * Examples:
 * <ul>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#UNIT_TEST Unit Test}
 * allows the player to choose between
 * {@link Effect#UNIT_TEST_PLUS_TWO_ACTIONS},
 * {@link Effect#UNIT_TEST_PLUS_TWO_MONEY}, or
 * {@link Effect#UNIT_TEST_PLUS_TWO_CARDS}.</li>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#TECHNICAL_DEBT_COLLECTION
 * Technical Debt Collection}
 * forces affected players to choose between
 * {@link Effect#TECHNICAL_DEBT_COLLECTION_DISCARD_TWO}
 * or {@link Effect#TECHNICAL_DEBT_COLLECTION_GAIN_BUG}.</li>
 * </ul>
 *
 * @param effect the effect being chosen
 *
 * @since 2.5
 */
public record ChooseEffectDecision(Effect effect) implements Decision {
    public ChooseEffectDecision {
        java.util.Objects.requireNonNull(effect, "effect must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return "Choose: " + effect.getDescription();
    }

    /**
     * Represents the possible effects that can be chosen.
     */
    public enum Effect {
        /**
         * Unit Test: +2 Actions.
         * <br/>
         * When chosen, the player gains 2 additional actions for this turn.
         */
        UNIT_TEST_PLUS_TWO_ACTIONS("+2 Actions"),

        /**
         * Unit Test: +$2.
         * <br/>
         * When chosen, the player gains $2 to spend for this turn.
         */
        UNIT_TEST_PLUS_TWO_MONEY("+$2"),

        /**
         * Unit Test: +2 Cards.
         * <br/>
         * When chosen, the player draws 2 cards.
         */
        UNIT_TEST_PLUS_TWO_CARDS("+2 Cards"),

        /**
         * Technical Debt Collection: Discard 2 cards.
         * <br/>
         * When chosen, the player must discard 2 cards from their hand.
         * This is a penalty option when affected by the Technical Debt Collection
         * attack card.
         */
        TECHNICAL_DEBT_COLLECTION_DISCARD_TWO("Discard 2 cards"),

        /**
         * Technical Debt Collection: Gain a Bug.
         * <br/>
         * When chosen, the player gains a Bug card to their discard pile.
         * This is a penalty option when affected by the Technical Debt Collection
         * attack card.
         */
        TECHNICAL_DEBT_COLLECTION_GAIN_BUG("Gain a Bug");

        private final String description;

        /**
         * Constructs an Effect with the specified description.
         *
         * @param description the description of the effect
         */
        Effect(String description) {
            this.description = java.util.Objects.requireNonNull(description, "Effect description must not be null");
        }

        /**
         * Gets the description of the effect.
         *
         * @return the description of the effect
         */
        public String getDescription() {
            return description;
        }
    }
}
