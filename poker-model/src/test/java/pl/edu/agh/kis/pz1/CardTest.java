package pl.edu.agh.kis.pz1;

import org.junit.Assert;
import org.junit.Test;


public class CardTest {

    private Card card1 = new Card(Card.Suit.PIK, Card.Rank.DEUCE);
    private Card card2 = new Card(Card.Suit.PIK, Card.Rank.ACE);
    private Card card3 = new Card(Card.Suit.KARO, Card.Rank.ACE);

    @Test
    public void compare() {

        Assert.assertEquals(-1,card1.compare(card2));
    }

    @Test
    public void sameCardByRank() {
        Assert.assertEquals(true, card2.sameCardByRank(card3));
        Assert.assertEquals(false, card1.sameCardByRank(card3));
    }

    @Test
    public void sameCardBySuit() {
        Assert.assertEquals(true, card1.sameCardBySuit(card2));
        Assert.assertEquals(false, card1.sameCardBySuit(card3));
    }

    @Test
    public void testTo_string() {
        Assert.assertEquals("PIK DEUCE",card1.to_string());
    }

    @Test
    public void testGetSuit() {
        Assert.assertEquals(Card.Suit.PIK,card1.getSuit());
    }

    @Test
    public void testGetRank() {
        Assert.assertEquals(Card.Rank.DEUCE,card1.getRank());
    }

    @Test
    public void testTestEquals() {
        Card cardToCompare = new Card(Card.Suit.PIK, Card.Rank.DEUCE);
        Assert.assertEquals(true,cardToCompare.equals(card1));
    }
}