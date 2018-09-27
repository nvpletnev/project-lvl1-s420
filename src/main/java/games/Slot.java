package games;


import games.controller.LossController;
import games.controller.MoveController;
import games.model.Game;
import games.model.Player;
import games.view.ViewGame;

public class Slot {

    public static void main(String... ___) {

        Player player = new Player(100);
        Game game = new Game(player, 100, 10);
        MoveController moveController = new MoveController(game);
        LossController lossController = new LossController(game);
        ViewGame viewGame = new ViewGame(game, moveController);

        while (!lossController.getLoss()) {
            viewGame.show();
            moveController.move();
            viewGame.showResult();
        }

    }
}
