package games;


import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {

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

            System.out.printf("У Вас %d$, ставка - %d$ \n", playerCredit, bet);
            System.out.println("Крутим барабаны! Розыгрыш принёс следующие результаты: ");

            firstCounter = (firstCounter + ((int) round(random() * 100))) % drum1.length;
            secondCounter= (secondCounter + ((int) round(random() * 100))) % drum2.length;
            thirdCounter = (thirdCounter + ((int) round(random() * 100))) % drum1.length;

            System.out.printf("Первый барабан - %d, второй - %d, третий - %d \n",
                    firstCounter, secondCounter, thirdCounter);

            if (firstCounter == secondCounter &&
                    firstCounter == thirdCounter) {
                playerCredit += win;

                System.out.printf("Выигрыш %d$, ваш капитал теперь составляет: %d$ \n", win, playerCredit);
                System.out.println(" ");
            } else {
                playerCredit -= bet;

                System.out.printf("Проигрыш %d$, ваш капитал теперь составляет: %d$ \n", bet, playerCredit);
                System.out.println(" ");
            }

        }
    }
}
