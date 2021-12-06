package pl.edu.agh.kis.pz1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PokerHandTest {

    private Card card1 = new Card(Card.Suit.PIK, Card.Rank.DEUCE);
    private Card card2 = new Card(Card.Suit.PIK, Card.Rank.ACE);
    private Card card3 = new Card(Card.Suit.KARO, Card.Rank.ACE);
    private Card card4 = new Card(Card.Suit.TREFL, Card.Rank.FOUR);
    private Card card5 = new Card(Card.Suit.KIER, Card.Rank.EIGHT);
    private Card card6 = new Card(Card.Suit.PIK, Card.Rank.TEN);
    private Card card7 = new Card(Card.Suit.PIK, Card.Rank.JACK);
    private Card card8 = new Card(Card.Suit.PIK, Card.Rank.QUEEN);
    private Card card9 = new Card(Card.Suit.PIK, Card.Rank.KING);
    private Card card10 = new Card(Card.Suit.TREFL, Card.Rank.ACE);
    ArrayList<Card> cards = new ArrayList<>();

    @Test
    public void score() {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        PokerHand pokerHand = new PokerHand(cards);

        Assert.assertEquals(HandType.PAIR.getHandTypeValue(),pokerHand.score());

        cards.clear();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card10);
        cards.add(card5);
        PokerHand pokerHand1 = new PokerHand(cards);

        Assert.assertEquals(HandType.THREE_OF_A_KIND.getHandTypeValue(),pokerHand1.score());
    }
}