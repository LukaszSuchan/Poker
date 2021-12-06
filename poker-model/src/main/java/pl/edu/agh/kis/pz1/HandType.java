/**
 * The HandType enum implements a handtype
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

public enum HandType {
    NOTHING(1),
    HIGH_CARD(2),
    PAIR(3),
    TWO_PAIR(4),
    THREE_OF_A_KIND(5),
    STRAIGHT(6),
    FLUSH(7),
    FULL_HOUSE(8),
    FOUR_OF_A_KIND(9),
    STRAIGHT_FLUSH(10);

    private int handTypeValue;

    private HandType(int value){this.handTypeValue = value;}

    public int getHandTypeValue() {
        return handTypeValue;
    }

}
