package games.controller;

import games.model.Game;
import games.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikolaypletnev on 27.09.18.
 */
public class LossControllerTest {
    @Test
    public void getLossWhenPlayerHasNoMoney() throws Exception {
        Player player = new Player(0);
        Game game = new Game(player, 1000, 10);
        LossController lossController = new LossController(game);

        assertTrue(lossController.getLoss());
    }

}