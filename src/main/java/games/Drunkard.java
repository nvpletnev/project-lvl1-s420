package games;

import org.apache.commons.math3.util.MathArrays;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by nikolaypletnev on 28.09.18.
 */
public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length;
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

    //init array card deck
    private static int[] cardDeck = new int[CARDS_TOTAL_COUNT];


    public static void main(String... ___) {

        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            cardDeck[i] = i;
        }

        Queue<Integer> player1Cards = new ArrayDeque<>(CARDS_TOTAL_COUNT);
        Queue<Integer> player2Cards = new ArrayDeque<>(CARDS_TOTAL_COUNT);

        MathArrays.shuffle(cardDeck);

        distributionOfCards(0, cardDeck.length / 2, player1Cards);
        distributionOfCards(cardDeck.length / 2, cardDeck.length, player2Cards);

        while (!(checkSizeZero(player1Cards, player2Cards))) {

            int card1 = player1Cards.poll() % PARS_TOTAL_COUNT;
            int card2 = player2Cards.poll() % PARS_TOTAL_COUNT;

            System.out.print("Игрок №1 карта: " + toString(card1) + "; ");
            System.out.print("Игрок №2 карта: " + toString(card2) + ". ");

            if (card1 > card2 || (card1 == 0 && card2 == 9)) {
                System.out.print("Выиграл игрок 1! ");
                player1Cards.offer(card1);
                player1Cards.offer(card2);
            } else if (card1 == card2) {
                player1Cards.offer(card1);
                player2Cards.offer(card2);
            } else {
                System.out.print("Выиграл игрок 2! ");
                player2Cards.offer(card2);
                player2Cards.offer(card1);
            }

            System.out.println("У игрока №1 " + player1Cards.size() + " карт, " +
                    "у игрока №2 " + (player2Cards.size()));
        }

    }

    private static boolean checkSizeZero(Queue queue1, Queue queue2) {
        return queue1.size() == 0 || queue2.size() == 0;
    }
    private static void distributionOfCards(int beginPosition, int endPosition, Queue queue) {
        for (int i = beginPosition; i < endPosition; i++) {
            queue.offer(cardDeck[i]);
        }
    }

    private static Suit getSuite(int cardNumber) {

        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {

        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {

        return getPar(cardNumber) + " " + getSuite(cardNumber);
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