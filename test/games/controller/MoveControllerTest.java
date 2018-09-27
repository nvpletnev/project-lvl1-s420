package games.controller;

import games.model.Game;
import games.model.Player;
import javafx.print.PageLayout;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by nikolaypletnev on 27.09.18.
 */
class MoveControllerTest {
    @Test
    void move() {

        Player player = new Player(100);
        Game game = new Game(player, 1000, 10);
        MoveController moveController = new MoveController(game);
        int firstCounterBeforeMove = moveController.getFirstCounter();
        int secondCounterBeforeMove = moveController.getSecondCounter();
        int thirdCounterBeforeMove = moveController.getThirdCounter();

        moveController.move();

        assertNotEquals(firstCounterBeforeMove, moveController.getFirstCounter());
        assertNotEquals(secondCounterBeforeMove, moveController.getSecondCounter());
        assertNotEquals(thirdCounterBeforeMove, moveController.getThirdCounter());

    }

    @Test
    void checkWin() {
        Player player = new Player(100);
        int resultBeforeMove = player.getPlayerCredit();
        Game game = new Game(player, 1000, 10);
        MoveController moveController = new MoveController(game);
        moveController.move();
        if (!moveController.checkWin()) {
            assertEquals(resultBeforeMove - game.getBet(), player.getPlayerCredit());
        }
        if (moveController.checkWin()) {
            assertEquals(resultBeforeMove + game.getWin(), player.getPlayerCredit());
        }
    }

}