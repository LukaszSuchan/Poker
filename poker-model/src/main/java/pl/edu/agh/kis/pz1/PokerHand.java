/**
 * The PokerHand class implements a pokerhand
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

import java.util.ArrayList;
import java.util.Collections;

public class PokerHand {
    /**
     * attributes
     */
    private ArrayList<Card> cards;
    private HandType handType;
    private Card temp;

    /**
     * Constructor
     * @param cards
     */
    public PokerHand(ArrayList<Card> cards){
        this.cards = cards;
    }

    public String to_string(){ return "("+ this.handType + ")"; }

    /**
     * checks which hand that is
     * @return score of a hand (value)
     */
    public int score(){
        if(straightFlush()) {
            handType=HandType.STRAIGHT_FLUSH;
            return HandType.STRAIGHT_FLUSH.getHandTypeValue();
        }
        else if(four_of_a_kind()){
            handType=HandType.FOUR_OF_A_KIND;
            return HandType.FOUR_OF_A_KIND.getHandTypeValue();}
        else if(fullHouse()) {
            handType=HandType.FULL_HOUSE;
            return HandType.FULL_HOUSE.getHandTypeValue();}
        else if(flush()){
            handType=HandType.FLUSH;
            return HandType.FLUSH.getHandTypeValue();}
        else if(straight()){
            handType=HandType.STRAIGHT;
            return HandType.STRAIGHT.getHandTypeValue();}
        else if(three_of_a_kind()) {
            handType=HandType.THREE_OF_A_KIND;
            return HandType.THREE_OF_A_KIND.getHandTypeValue();}
        else if(two_pair()){
            handType=HandType.TWO_PAIR;
            return HandType.TWO_PAIR.getHandTypeValue();}
        else if (pair()){
            handType=HandType.PAIR;
            return HandType.PAIR.getHandTypeValue();}
        else {
            handType=HandType.NOTHING;
            return HandType.NOTHING.getHandTypeValue();
        }
    }

    /**
     * checks if it is a pair
     * @return boolean
     */
    private boolean pair(){
        for(Card c : cards){
            for(Card d : cards){
                if(c.equals(d)) continue;
                if(c.sameCardByRank(d)){
                    temp = c;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * helper
     * @param c
     * @return boolean
     */
    private boolean pairCheck(Card c) {
        for (Card d : cards) {
            if (c.equals(d)) continue;
            if (c.sameCardByRank(d)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if it is a two pair
     * @return boolean
     */
    private boolean two_pair(){
        boolean first = false;
        Card firstPairCard = null;
        if(pair()){
            first = true;
            firstPairCard = temp;
        }
        if(first){
            for(Card c : cards){
                if(!c.sameCardByRank(firstPairCard)){
                    if(pairCheck(c)) return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if it is a three of a kind
     * @return boolean
     */
    private boolean three_of_a_kind(){
        for(Card c : cards){
            int count = 1;
            for(Card d : cards){
                if(c.equals(d)) continue;
                if(c.sameCardByRank(d)){
                    temp = c;
                    count++;
                }
            }
            if(count>2){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if it is a four of a kind
     * @return boolean
     */
    private boolean four_of_a_kind(){
        for(Card c : cards){
            int count = 1;
            for(Card d : cards){
                if(c.equals(d)) continue;
                if(c.sameCardByRank(d)){
                    count++;
                }
            }
            if(count>3){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if it is a straight
     * @return boolean
     */
    private boolean straight(){
        ArrayList<Card.Rank> nums = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            nums.add(cards.get(i).getRank());
        }
        Collections.sort(nums);
        int x = nums.get(0).getRank();
        for(int i = 1; i < 5 ; i++){
            if(nums.get(i).getRank() != x+1) return false;

            x++;
        }
        return true;
    }

    /**
     * checks if it is a flush
     * @return boolean
     */
    private boolean flush(){
        Card first = cards.get(0);
        for(Card c :cards){
            if(!c.sameCardBySuit(first)) return false;
        }
        return true;
    }

    /**
     * checks if it is a full house
     * @return boolean
     */
    private boolean fullHouse(){
        boolean first = false;
        Card firstPairCard = null;
        if(three_of_a_kind()){
            first = true;
            firstPairCard = temp;
        }
        if(first){
            for(Card c : cards){
                if(!c.sameCardByRank(firstPairCard)) {
                    if(pairCheck(c)) return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if it is a poker
     * @return boolean
     */
    private boolean straightFlush(){
        return (straight()&&flush());
    }
}
