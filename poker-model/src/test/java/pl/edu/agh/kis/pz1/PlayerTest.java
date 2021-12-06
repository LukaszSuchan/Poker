package pl.edu.agh.kis.pz1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class PlayerTest {

    private Player player1 = new Player();
    private Card card1 = new Card(Card.Suit.PIK, Card.Rank.DEUCE);
    private Card card2 = new Card(Card.Suit.PIK, Card.Rank.ACE);
    private Card card3 = new Card(Card.Suit.KARO, Card.Rank.ACE);
    private Card card4 = new Card(Card.Suit.TREFL, Card.Rank.FOUR);
    private Card card5 = new Card(Card.Suit.KIER, Card.Rank.EIGHT);
    private ArrayList<String> hand = new ArrayList<>();

    @Test
    public void showCards() {
        player1.getCards().add(card1);
        player1.getCards().add(card2);
        player1.getCards().add(card3);
        player1.getCards().add(card4);
        player1.getCards().add(card5);
        hand.add("1.PIK DEUCE");
        hand.add("2.PIK ACE");
        hand.add("3.KARO ACE");
        hand.add("4.TREFL FOUR");
        hand.add("5.KIER EIGHT");
        hand.add("(PAIR)");

        Assert.assertEquals(hand,player1.showCards());

    }

    @Test
    public void placeBet() {

        player1.placeBet(15);
        Assert.assertEquals(10,player1.getPlacedBet());
    }
}