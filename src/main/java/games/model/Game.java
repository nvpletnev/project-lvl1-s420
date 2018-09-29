package games.model;

/**
 * Created by nikolaypletnev on 26.09.18.
 */
public class Game {

    private static final int DEFAULT_DRUM_COUNT = 3;
    private static final int DEFAULT_COUNT_SYMBOLS = 7;

    private final Player player;
    private final Drum[] drums = new Drum[DEFAULT_DRUM_COUNT];
    private int bet;
    private int win;

    public Game(final Player player, final int win, final int bet) {

        this.player = player;
        this.win = win;
        this.bet = bet;

        //create drums
        for (int i = 0; i < drums.length; i++) {
            drums[i] = new Drum(DEFAULT_COUNT_SYMBOLS);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Drum[] getDrums() {
        return drums;
    }

    public int getBet() {
        return bet;
    }

    public int getWin() {
        return win;
    }
}
