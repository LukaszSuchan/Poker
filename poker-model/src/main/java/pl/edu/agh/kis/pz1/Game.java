/**
 * The Game class implements a game
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

import java.util.ArrayList;

public class Game {

    /**
     * attributes
     */
    private ArrayList<Player> players;
    private ArrayList<Player> winners;
    private int numberOfPlayers;
    private int ante;
    private Deck deck;
    private int pot;
    private Player tie;

    /**
     * Constructor
     * @param numberOfPlayers
     */
    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.deck.shuffle();
        this.pot = 0;
        tie = new Player();
        ante = 5;
    }

    /**
     * give cards to players
     * @param p
     */
    public void addPlayer(Player p) {
        for (int i = 0; i < 5; i++) {
            p.addCard(this.deck);
        }
        addToPot(p.placeBet(ante));
    }

    /**
     * compare hand of players and return player with better hand
     * @param a
     * @param b
     * @return Player
     */
    private Player compareHands(Player a, Player b) {
        PokerHand aHand = new PokerHand(a.getCards());
        PokerHand bHand = new PokerHand(b.getCards());
        int aScore = aHand.score();
        int bScore = bHand.score();

        if (aScore > bScore) {
            return a;
        } else if (bScore > aScore) {
            return b;
        } else return tie;
    }

    /**
     * gets winner or winners of the game
     * @return ArrayList<Players>
     */
    public ArrayList<Player> getWinner() {
        ArrayList<Player> winners = new ArrayList<>();
        for (Player p : players) {
            if (p.fold) continue;

            winners.add(p);
            break;
        }
        if (winners.isEmpty()) return players;

        for (Player p : players) {
            if (p.fold) continue;

            if (!p.equals(winners.get(0))) {
                Player compare = compareHands(p, winners.get(0));
                if (compare.equals(tie)) {
                    winners.add(p);
                    continue;
                } else if (compare.equals(p)) {
                    winners.clear();
                    winners.add(p);
                }
            }
        }

        return winners;
    }

    /**
     * adds to pot an amount
     * @param amount
     */
    public void addToPot(int amount){
        pot+=amount;
    }

    public int getPot() {
        return pot;
    }

    public void givePotToWinner(Player w){
        w.money+=pot;
        pot = 0;
    }

    /**
     * split pot between winners when is a tie
     * @param winners
     */
    public void splitPot(ArrayList<Player> winners){
        int split = pot/winners.size();
        for(Player winner : winners){
            winner.money+=split;
        }
        pot = 0;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
