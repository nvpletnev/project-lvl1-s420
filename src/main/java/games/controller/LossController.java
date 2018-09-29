package games.controller;

import games.model.Game;

/**
 * Created by nikolaypletnev on 27.09.18.
 */

public class LossController {

    private final Game game;

    public LossController (Game game) {
        this.game = game;
    }

    public boolean getLoss() {

        return game.getPlayer().getPlayerCredit() <= 0;
    }
}
