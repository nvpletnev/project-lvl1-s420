package games.controller;

import games.model.Drum;
import games.model.Game;

import static java.lang.Math.random;
import static java.lang.Math.round;

/**
 * Created by nikolaypletnev on 27.09.18.
 */
public class MoveController {


    private final Game game;
    private int firstCounter;
    private int secondCounter;
    private int thirdCounter;

    public MoveController(Game game) {

        this.game = game;
        this.firstCounter = 0;
        this.secondCounter = 0;
        this.thirdCounter = 0;
    }

    public int getFirstCounter() {
        return firstCounter;
    }

    public int getSecondCounter() {
        return secondCounter;
    }

    public int getThirdCounter() {
        return thirdCounter;
    }

    public void move() {

        //place a bet
        game.getPlayer().setPlayerCredit(game.getPlayer().getPlayerCredit() - game.getBet());

        //move
        firstCounter = (firstCounter + ((int) round(random() * 100))) % game.getDrums()[0].getGameSymbols().size();
        secondCounter = (secondCounter + ((int) round(random() * 100))) % game.getDrums()[1].getGameSymbols().size();
        thirdCounter = (thirdCounter + ((int) round(random() * 100))) % game.getDrums()[2].getGameSymbols().size();

        if (checkWin()) {
            //set player credit if win
            int win = game.getPlayer().getPlayerCredit() + game.getWin();
            game.getPlayer().setPlayerCredit(win);
        }

    }

    public boolean checkWin() {
        return firstCounter == secondCounter &&
               firstCounter == thirdCounter;
    }
}
