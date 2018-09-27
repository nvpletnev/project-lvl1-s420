package games.model;

/**
 * Created by nikolaypletnev on 26.09.18.
 */
public class Player {

    private int PlayerCredit;

    public Player(final int PlayerCredit) {
        if (PlayerCredit < 0) {
            this.PlayerCredit = 0;
        } else this.PlayerCredit = PlayerCredit;
    }

    public int getPlayerCredit() {
        return PlayerCredit;
    }

    public void setPlayerCredit(int playerCredit) {
        PlayerCredit = playerCredit;
    }
}
