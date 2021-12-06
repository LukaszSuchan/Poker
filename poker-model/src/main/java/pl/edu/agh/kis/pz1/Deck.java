/**
 * The Deck class implements a deck
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

import java.security.SecureRandom;
import java.util.ArrayList;


public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<>();

        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 13 ; j++){
                Card card = new Card(Card.Suit.values()[i], Card.Rank.values()[j]);
                cards.add(card);
            }
    }

    /**
     * returns suit and rank for every card in a deck
     */
    public void getDeck(){
        for(Card c: this.cards)
            System.out.println(c.getSuit() + " " + c.getRank());
    }

    /**
     * sorts cards by rank and suit
     */
    public void sort(){
        ArrayList<Card> sortedDeck = new ArrayList<>();

        while(!this.cards.isEmpty()){
            Card i = this.cards.get(0);
            for(Card c: this.cards)
                if(i.compare(c) < 0)
                    i = c;
            sortedDeck.add(i);
            this.cards.remove(i);
        }
        this.cards = sortedDeck;
    }


    /**
     * shuffles the deck
     */
    public void shuffle(){
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        SecureRandom random = new SecureRandom();

        while(!this.cards.isEmpty()){
            int i = random.nextInt(this.cards.size());
            shuffledDeck.add(this.cards.get(i));
            this.cards.remove(i);
        }
        this.cards = shuffledDeck;
    }


    /**
     * takes a card from the top and returns it
     * @return Card
     */
    public Card takeACard(){
        Card card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }
}
