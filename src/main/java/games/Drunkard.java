package games;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by nikolaypletnev on 28.09.18.
 */
public class Drunkard {

    //init array card deck
    private static int[] cardDeck = CardUtils.getShaffledCards();

    public static void main(String... ___) {

        Queue<Integer> player1Cards = new ArrayDeque<>(CardUtils.CARDS_TOTAL_COUNT);
        Queue<Integer> player2Cards = new ArrayDeque<>(CardUtils.CARDS_TOTAL_COUNT);

        distributionOfCards(0, cardDeck.length / 2, player1Cards);
        distributionOfCards(cardDeck.length / 2, cardDeck.length, player2Cards);

        while (!(checkSizeZero(player1Cards, player2Cards))) {

            int card1 = player1Cards.poll();
            int card2 = player2Cards.poll();

            System.out.print("Игрок №1 карта: " + CardUtils.toString(card1) + "; ");
            System.out.print("Игрок №2 карта: " + CardUtils.toString(card2) + ". ");

            if (((card1 % CardUtils.PARS_TOTAL_COUNT > card2 % CardUtils.PARS_TOTAL_COUNT) &&
                    !(checkSixAndAce(card2, card1))) ||
                    checkSixAndAce(card1, card2)) {

                System.out.print("Выиграл игрок 1! ");
                player1Cards.offer(card1);
                player1Cards.offer(card2);
            } else if (card1 % CardUtils.PARS_TOTAL_COUNT == card2 % CardUtils.PARS_TOTAL_COUNT) {
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

    private static boolean checkSixAndAce(int card1, int card2) {
        if (card1 % CardUtils.PARS_TOTAL_COUNT == 0 && card2 % CardUtils.PARS_TOTAL_COUNT == 8) {
            return true;
        } return false;
    }

    private static boolean checkSizeZero(Queue queue1, Queue queue2) {
        return queue1.size() == 0 || queue2.size() == 0;
    }

    private static void distributionOfCards(int beginPosition, int endPosition, Queue queue) {
        for (int i = beginPosition; i < endPosition; i++) {
            queue.offer(cardDeck[i]);
        }
    }

}