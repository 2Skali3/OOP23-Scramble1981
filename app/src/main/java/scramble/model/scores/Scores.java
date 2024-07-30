package scramble.model.scores;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;

/**
 * This class handles the score board.
 * It creates a list to be display in the start menu.
 */
public class Scores {

    private static final int MAX = 9;
    private static final int PTS_STD = 10_000;
    private final List<Integer> scoresList;

    /**
     * Class constructor.
     */
    public Scores() {
        scoresList = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            scoresList.add(PTS_STD);
        }
    }

    /**
     * Adds a score to the scoreboard.
     * 
     * @param score the new score
     */
    public void addScore(final int score) {
        for (int i = 0; i < MAX; i++) {
            if (score > scoresList.get(i)) {
                scoresList.set(i, score);
            }
        }
    }

    /**
     * Getter for the score's list.
     * SpotBUgs warning suppressed since it returns a std class.
     * 
     * @return the score's list
     */
    @SuppressFBWarnings
    public List<Integer> getScoresList() {
        return scoresList;
    }

    /**
     * This method clears the scoreboard.
     */
    public void resetScores() {
        scoresList.clear();
    }

}
