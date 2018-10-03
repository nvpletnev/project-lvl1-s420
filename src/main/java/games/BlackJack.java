package games;

import org.slf4j.Logger;

import java.io.IOException;

import static games.Choice.getCharacterFromUser;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

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

            log.info("Вам выпала карта {}", CardUtils.toString(addCard2Player(0)));
            log.info("Вам выпала карта {}", CardUtils.toString(addCard2Player(0)));

            for (int i = 0; i < 5; i++) {
                if (sum(0) < 20) {
                    if (confirm("Берем еще? ")) {
                        log.info("Вам выпала карта {}", CardUtils.toString(addCard2Player(0)));
                    } else break;
                }
            }

            //ход компьютера
            log.info("Компьютеру выпала карта {}", CardUtils.toString(addCard2Player(1)));
            log.info("Компьютеру выпала карта {}", CardUtils.toString(addCard2Player(1)));

            for (int i = 0; i < 5; i++) {
                if (sum(1) < 17 ) {
                    log.info("Компьютер решил взять ещё и ему выпала карта {}",
                            CardUtils.toString(addCard2Player(1)));
                }

            }

            log.info("Сумма ваших очков - {}, компьютера - {}\n",
                    getFinalSum(0), getFinalSum(1));

            if (getFinalSum(0) > getFinalSum(1)) {
                log.info("Вы выиграли раунд! Получаете 10$\n");
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
            }else if (getFinalSum(0) == getFinalSum(1)) {
                log.info("Ничья!\n");
            }else {
               log.info("Вы проиграли раунд! Теряете 10$\n");
                playersMoney[0] -= 10;
                playersMoney[1] += 10;
            }

        }

        if (playersMoney[0] > 0)
            log.info("Вы выиграли! Поздравляем!");
        else
            log.info("Вы проиграли! Соболезнуем!");
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
        log.info("У Вас {}$, у компьютера - {}$. Начинаем новый раунд!", playersMoney[0], playersMoney[1]);
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
       log.info(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }
}
