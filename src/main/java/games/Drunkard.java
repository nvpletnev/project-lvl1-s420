package games;

import org.slf4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by nikolaypletnev on 28.09.18.
 */
public class Drunkard {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);

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

           log.info("Игрок №1 карта: {}; Игрок №2 карта: {};",
                   CardUtils.toString(card1), CardUtils.toString(card2));

            if (((card1 % CardUtils.PARS_TOTAL_COUNT > card2 % CardUtils.PARS_TOTAL_COUNT) &&
                    !(checkSixAndAce(card2, card1))) ||
                    checkSixAndAce(card1, card2)) {
                log.info("Выиграл игрок 1! ");
                player1Cards.offer(card1);
                player1Cards.offer(card2);
            } else if (card1 % CardUtils.PARS_TOTAL_COUNT == card2 % CardUtils.PARS_TOTAL_COUNT) {
                player1Cards.offer(card1);
                player2Cards.offer(card2);
            } else {
                log.info("Выиграл игрок 2! ");
                player2Cards.offer(card2);
                player2Cards.offer(card1);
            }

            log.info("У игрока №1 {} карт, у игрока №2 {} карт", player1Cards.size(), (player2Cards.size()));
        }
    }

    private static boolean checkSixAndAce(int card1, int card2) {
        return (card1 % CardUtils.PARS_TOTAL_COUNT == 0 && card2 % CardUtils.PARS_TOTAL_COUNT == 8);
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