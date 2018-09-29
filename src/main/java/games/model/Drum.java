package games.model;

import java.util.ArrayList;

/**
 * Created by nikolaypletnev on 26.09.18.
 */
public class Drum {

    private final ArrayList<Integer> gameSymbols;

    public Drum(int symbolsCountOnDrum) {

        gameSymbols = new ArrayList<Integer>();

        for (int i = 0; i < symbolsCountOnDrum; i++) {
            gameSymbols.add(i);
        }
    }

    public String toString() {
        return gameSymbols.toString();
    }

    public ArrayList<Integer> getGameSymbols() {
        return gameSymbols;
    }
}
