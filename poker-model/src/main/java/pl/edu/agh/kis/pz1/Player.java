/**
 * The Player class implements a player
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

import java.util.ArrayList;

public class Player {
    /**
     * attributes
     */
    private ArrayList<Card> cards;
    public int money;
    private int placedBet;
    public boolean fold;
    private PokerHand pokerHand;

    /**
     * Constructor
     */
    public Player(){
        cards = new ArrayList<>();
        money = 100;
        fold = false;
        placedBet = 0;
    }

    /**
     * gives hand in strings
     * @return ArrayList<String>
     */
    public ArrayList<String> showCards(){
        setPokerHand(new PokerHand(cards));
        int value = pokerHand.score();
        ArrayList<String> cardsStr = new ArrayList<>();
        int i = 1;
        for(Card c: cards){
            String number = String.valueOf(i);
            cardsStr.add(number+ "." +c.to_string());
            i++;
        }
        cardsStr.add(pokerHand.to_string());
        return cardsStr;
    }

    /**
     * adds card to player
     * @param deck
     */
    public void addCard(Deck deck){ this.cards.add(deck.takeACard()); }

    /**
     * exchanges card of player when client decides to do that
     * @param i
     * @param deck
     */
    public void exchangeCard(int i, Deck deck){
        cards.set(i-1, deck.takeACard());
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public PokerHand getPokerHand() {
        return pokerHand;
    }

    public void setPokerHand(PokerHand pokerHand) {
        this.pokerHand = pokerHand;
    }

    public void sort(){
        ArrayList<Card> sortedHand = new ArrayList<>();

        while(this.cards.size() != 0){
            Card i = this.cards.get(0);
            for(Card c: this.cards)
                if(i.compare(c) < 0)
                    i = c;
            sortedHand.add(i);
            this.cards.remove(i);
        }
        this.cards = sortedHand;
    }

    /**
     * places bet of player
     * @param amount
     * @return int
     */
    public int placeBet(int amount){
        money-=amount;
        placedBet += amount;
        return amount;
    }

    public int getPlacedBet() {
        return placedBet - 5;
    }

    /**
     * clears hand
     */
    public void emptyHand(){
        cards.clear();
    }
}
