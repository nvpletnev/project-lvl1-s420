package games;

import java.io.IOException;

import static games.Choice.getCharacterFromUser;

public class BlackJack {

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока

    private static int[] playersMoney = {100, 100};

    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;

    public static void main(String... ___) throws IOException {

        while (playersMoney[0] > 0 || playersMoney[1] > 0) {

            initRound();

            System.out.println("Вам выпала карта " + CardUtils.toString(addCard2Player(0)));
            System.out.println("Вам выпала карта " + CardUtils.toString(addCard2Player(0)));

            for (int i = 0; i < 5; i++) {
                if (sum(0) < 20) {
                    if (confirm("Берем еще? ")) {
                        System.out.println("Вам выпала карта " + CardUtils.toString(addCard2Player(0)));
                    } else break;
                }
            }

            //ход компьютера
            System.out.println("Компьютеру выпала карта " + CardUtils.toString(addCard2Player(1)));
            System.out.println("Компьютеру выпала карта " + CardUtils.toString(addCard2Player(1)));

            for (int i = 0; i < 5; i++) {
                if (sum(1) < 17 ) {
                    System.out.println("Компьютер решил взять ещё и ему выпала карта " +
                            CardUtils.toString(addCard2Player(1)));
                }

            }

            System.out.printf("Сумма ваших очков - %d, компьютера - %d\n",
                    getFinalSum(0), getFinalSum(1));

            if (getFinalSum(0) > getFinalSum(1)) {
                System.out.println("Вы выиграли раунд! Получаете 10$");
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
            } else {
                System.out.println("Вы проиграли раунд! Теряете 10$");
                playersMoney[0] -= 10;
                playersMoney[1] += 10;
            }

        }

        if (playersMoney[0] > 0)
            System.out.println("Вы выиграли! Поздравляем!");
        else
            System.out.println("Вы проиграли! Соболезнуем!");
    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK: return 2;
            case QUEEN: return 3;
            case KING: return 4;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case ACE:
            default: return 11;
        }
    }

    private static void initRound() {
        System.out.println("\nУ Вас " + playersMoney[0] + "$, у компьютера - "
                + playersMoney[1] + "$. Начинаем новый раунд!");
        cards = CardUtils.getShaffledCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
    }

    /**
     * Реализуйте метод, извлекающий следующую карту из колоды и помещающий её в стопку карт
     * указанного игрока.
     * Он может иметь следующую сигнатуру:
     * */
    private static int addCard2Player(int player) {
        // todo: реализуйте!
        return playersCards[player][playersCursors[player]++] = cards[cursor++];
    }

    /**
     * Теперь реализуйте метод, который будет суммировать очки игрока.
     * Он может иметь следующую сигнатуру:
     * */
    static int sum(int player) {
        // todo: реализуйте!
        int sum = 0;
        for (int i = 0; i < playersCursors[player]; i++) {
            sum += value(playersCards[player][i]);
        }
        return sum;
    }

    /**
     * сделайте метод, который вызывал бы метод sum и возвращал бы кол-во очков
     * только если оно не превышает максимального значения - 21, в ином случае возвращал бы 0:
     * */
    static int getFinalSum(int player) {
        // todo: реализуйте!
        if (sum(player) <= MAX_VALUE)
            return sum(player);
        else return 0;
    }

    static boolean confirm(String message) throws IOException {
        System.out.println(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }
}
