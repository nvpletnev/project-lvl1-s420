package games;

import org.apache.commons.math3.util.MathArrays;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by nikolaypletnev on 28.09.18.
 */
public class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

    //init array card deck
    private static int[] cardDeck = new int[CARDS_TOTAL_COUNT];

    private static int[] beginCursor = new int[2];
    private static int[] endCursor = new int[2];

    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];


    public static void main(String... ___) {

        //init card deck
        for (int i = 0; i < cardDeck.length; i++) {
            cardDeck[i] = i;
        }

        MathArrays.shuffle(cardDeck);

        System.arraycopy(cardDeck, 0, playersCards[0],
                0, cardDeck.length / 2);

        System.arraycopy(cardDeck, cardDeck.length / 2, playersCards[1],
                0, cardDeck.length / 2);

        endCursor[0] = cardDeck.length / 2;
        endCursor[1] = cardDeck.length / 2;
        //TODO

        while (endCursor[0] - beginCursor[0] > 0 ||
                endCursor[1] - beginCursor[1] > 0) {

            System.out.print("Игрок №1 карта: " + toString(playersCards[0][beginCursor[0]]) + "; ");
            System.out.print("Игрок №2 карта: " + toString(playersCards[1][beginCursor[1]]) + ". ");

            if (playersCards[0][beginCursor[0]] > playersCards[1][beginCursor[1]]) {

                System.out.print("Выиграл игрок 1! ");

                endCursor[0] = incrementIndex(endCursor[0]);
                playersCards[0][endCursor[0]] = playersCards[1][beginCursor[1]];
                endCursor[0] = incrementIndex(endCursor[0]);
                playersCards[0][endCursor[0]] = playersCards[0][beginCursor[0]];
                beginCursor[0] = incrementIndex(beginCursor[0]);
                beginCursor[1] = incrementIndex(beginCursor[1]);

            } else {
                System.out.print("Выиграл игрок 2! ");
                endCursor[1] = incrementIndex(endCursor[1]);
                playersCards[1][endCursor[1]] = playersCards[1][beginCursor[0]];
                endCursor[1] = incrementIndex(endCursor[1]);
                playersCards[1][endCursor[1]] = playersCards[1][beginCursor[1]];
                beginCursor[0] = incrementIndex(beginCursor[0]);
                beginCursor[1] = incrementIndex(beginCursor[1]);
            }
            System.out.println("У игрока №1 " + (endCursor[0] - beginCursor[0]) + " карт, " +
                    "у игрока №2 " + (endCursor[1] - beginCursor[1]));
        }
    }

    private static Suit getSuite(int cardNumber) {

        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {

        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    @NotNull
    private static String toString(int cardNumber) {

        return getPar(cardNumber) + " " + getSuite(cardNumber);
    }

    private static int incrementIndex(int i) {
        return (i + 1) % CARDS_TOTAL_COUNT;
    }

    private enum Suit {

        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    private enum Par {
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK, // Валет
        QUEEN, // Дама
        KING, // Король
        ACE // Туз

    }
}