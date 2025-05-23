package edu.brandeis.cosi.atg.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import edu.brandeis.cosi.atg.state.GameState;
import edu.brandeis.cosi.atg.state.Hand;
import edu.brandeis.cosi.atg.state.CardStacks;
import edu.brandeis.cosi.atg.cards.Card;
import edu.brandeis.cosi.atg.decisions.BuyDecision;
import edu.brandeis.cosi.atg.decisions.DiscardCardDecision;
import edu.brandeis.cosi.atg.decisions.EndPhaseDecision;
import edu.brandeis.cosi.atg.decisions.GainCardDecision;
import edu.brandeis.cosi.atg.decisions.PlayCardDecision;
import edu.brandeis.cosi.atg.decisions.TrashCardDecision;
import edu.brandeis.cosi.atg.event.DiscardCardEvent;
import edu.brandeis.cosi.atg.event.EndTurnEvent;
import edu.brandeis.cosi.atg.event.GainCardEvent;
import edu.brandeis.cosi.atg.event.GameEvent;
import edu.brandeis.cosi.atg.event.PlayCardEvent;
import edu.brandeis.cosi.atg.event.TrashCardEvent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonSerialization {
    private void testJsonRoundTrip(Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new GuavaModule());

        String json = objectMapper.writeValueAsString(o);
        assertNotNull(json);

        Object deserializedObject = objectMapper.readValue(json, o.getClass());
        assertNotNull(deserializedObject);
        assertEquals(o.getClass(), deserializedObject.getClass());
        assertEquals(o, deserializedObject);
    }

    @Test
    public void testGainCardEventSerialization() throws Exception {
        testJsonRoundTrip(new GainCardEvent(Card.Type.FRAMEWORK, "Alice"));
    }

    @Test
    public void testDiscardCardEventSerialization() throws Exception {
        testJsonRoundTrip(new DiscardCardEvent(Card.Type.FRAMEWORK, "Bob"));
    }

    @Test
    public void testEndTurnEventSerialization() throws Exception {
        testJsonRoundTrip(new EndTurnEvent());
    }

    @Test
    public void testGameEventSerialization() throws Exception {
        testJsonRoundTrip(new GameEvent("GameStarted"));
    }

    @Test
    public void testPlayCardEventSerialization() throws Exception {
        testJsonRoundTrip(new PlayCardEvent(new Card(Card.Type.FRAMEWORK, 5), "Dave"));
    }

    @Test
    public void testTrashCardEventSerialization() throws Exception {
        testJsonRoundTrip(new TrashCardEvent(Card.Type.FRAMEWORK, "Eve"));
    }

    @Test
    public void testBuyCardDecisionSerialization() throws Exception {
        testJsonRoundTrip(new BuyDecision(Card.Type.FRAMEWORK));
    }

    @Test
    public void testDiscardCardDecisionSerialization() throws Exception {
        testJsonRoundTrip(new DiscardCardDecision(new Card(Card.Type.FRAMEWORK, 5)));
    }

    @Test
    public void testEndTurnDecisionSerialization() throws Exception {
        testJsonRoundTrip(new EndPhaseDecision(GameState.TurnPhase.BUY));
    }

    @Test
    public void testGainCardDecisionSerialization() throws Exception {
        testJsonRoundTrip(new GainCardDecision(Card.Type.FRAMEWORK));
    }

    @Test
    public void testPlayCardDecisionSerialization() throws Exception {
        testJsonRoundTrip(new PlayCardDecision(new Card(Card.Type.FRAMEWORK, 5)));
    }

    @Test
    public void testTrashCardDecisionSerialization() throws Exception {
        testJsonRoundTrip(new TrashCardDecision(new Card(Card.Type.FRAMEWORK, 5)));
    }

    @Test
    public void testHandSerialization() throws Exception {
        ImmutableCollection<Card> playedCards = ImmutableSet.of(new Card(Card.Type.FRAMEWORK, 5));
        ImmutableCollection<Card> unplayedCards = ImmutableSet.of(new Card(Card.Type.BITCOIN, 3));
        Hand hand = new Hand(playedCards, unplayedCards);

        testJsonRoundTrip(hand);
    }

    @Test
    public void testGameDeckSerialization() throws Exception {
        ImmutableMap<Card.Type, Integer> cardCounts = ImmutableMap.of(
                Card.Type.FRAMEWORK, 10,
                Card.Type.BITCOIN, 5);
        CardStacks cardStacks = new CardStacks(cardCounts);

        testJsonRoundTrip(cardStacks);
    }

    @Test
    public void testGameStateSerialization() throws Exception {
        ImmutableCollection<Card> playedCards = ImmutableSet.of(new Card(Card.Type.FRAMEWORK, 5));
        ImmutableCollection<Card> unplayedCards = ImmutableSet.of(new Card(Card.Type.FRAMEWORK, 3));
        Hand hand = new Hand(playedCards, unplayedCards);

        ImmutableMap<Card.Type, Integer> cardCounts = ImmutableMap.of(
                Card.Type.FRAMEWORK, 10,
                Card.Type.BITCOIN, 5);
        CardStacks gameDeck = new CardStacks(cardCounts);

        GameState gameState = new GameState(
                "Alice",
                hand,
                GameState.TurnPhase.ACTION,
                2,
                3,
                1,
                gameDeck);

        testJsonRoundTrip(gameState);
    }
}