package scramble1981_remastered.model.scores;

import java.util.*;

public class Scores {

    private static final int MAX = 9;
    private static final int PTS_STD = 10000;
    private List<Integer> scores;

    public Scores() {
        scores = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            scores.add(PTS_STD);
        }
    }

    public void addScore(int score) {
        for (int i = 0; i < MAX; i++) {
            if (score > scores.get(i)) {
                scores.set(i, score);
            }
        }
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void resetScores() {
        scores.clear();
    }
}
