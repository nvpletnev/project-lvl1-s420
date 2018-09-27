package games.view;

import games.controller.MoveController;
import games.model.Game;

/**
 * Created by nikolaypletnev on 27.09.18.
 */
public class ViewGame {

    private final Game game;
    private final MoveController moveController;

    public ViewGame(final Game game, final MoveController moveController) {
        this.game = game;
        this.moveController = moveController;
    }

    public void show(){
        int playerCredit = game.getPlayer().getPlayerCredit();
        int bet = game.getBet();
        System.out.printf("У Вас %d$, ставка - %d$ \n", playerCredit, bet);
        System.out.println("Крутим барабаны! Розыгрыш принёс следующие результаты: ");


    }
    public void showResult() {
        System.out.printf("Первый барабан - %d, второй - %d, третий - %d \n", moveController.getFirstCounter(), moveController.getSecondCounter(), moveController.getThirdCounter());

        if (moveController.checkWin()) {
            System.out.printf("Выигрыш %d$, ваш капитал теперь составляет: %d$ \n", game.getWin(), game.getPlayer().getPlayerCredit());
            System.out.println(" ");
        } else {
            System.out.printf("Проигрыш %d$, ваш капитал теперь составляет: %d$ \n", game.getBet(), game.getPlayer().getPlayerCredit());
            System.out.println(" ");
        }

    }

}
