package games;


import org.slf4j.Logger;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    public static void main(String... ___) {

        int playerCredit = 100;
        int bet = 10;
        int win = 1000;

        int[] drum1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] drum2 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] drum3 = new int[]{1, 2, 3, 4, 5, 6, 7};

        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;

        while (playerCredit > 0) {

            log.info("У Вас {}$, ставка - {}$", playerCredit, bet);
            log.info("Крутим барабаны! Розыгрыш принёс следующие результаты: ");

            firstCounter = (firstCounter + ((int) round(random() * 100))) % drum1.length;
            secondCounter= (secondCounter + ((int) round(random() * 100))) % drum2.length;
            thirdCounter = (thirdCounter + ((int) round(random() * 100))) % drum3.length;

            log.info("Первый барабан - {}, второй - {}, третий - {} ",
                    firstCounter, secondCounter, thirdCounter);

            if (firstCounter == secondCounter &&
                    firstCounter == thirdCounter) {
                playerCredit += win;
                log.info("Выигрыш {}$, ваш капитал теперь составляет: {}$ \n", win, playerCredit);
            } else {
                playerCredit -= bet;
                log.info("Проигрыш {}$, ваш капитал теперь составляет: {}$ \n", bet, playerCredit);
            }

        }
    }
}
