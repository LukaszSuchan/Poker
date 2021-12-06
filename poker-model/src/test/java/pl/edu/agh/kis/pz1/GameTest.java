package pl.edu.agh.kis.pz1;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class GameTest {

    private Game game = new Game(2);
    private Player player1 = new Player();
    private Player player2 = new Player();
    private ArrayList<Player> winner = new ArrayList<>();
    private Card card1 = new Card(Card.Suit.PIK, Card.Rank.DEUCE);
    private Card card2 = new Card(Card.Suit.PIK, Card.Rank.ACE);
    private Card card3 = new Card(Card.Suit.KARO, Card.Rank.ACE);
    private Card card4 = new Card(Card.Suit.TREFL, Card.Rank.FOUR);
    private Card card5 = new Card(Card.Suit.KIER, Card.Rank.EIGHT);
    private Card card6 = new Card(Card.Suit.TREFL, Card.Rank.NINE);

    @Test
    public void getWinner() {
        player1.getCards().add(card1);
        player1.getCards().add(card2);
        player1.getCards().add(card3);
        player1.getCards().add(card4);
        player1.getCards().add(card5);
        player2.getCards().add(card1);
        player2.getCards().add(card2);
        player2.getCards().add(card6);
        player2.getCards().add(card4);
        player2.getCards().add(card5);

        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        winner.add(player1);
        Assert.assertEquals(winner, game.getWinner());
    }
}