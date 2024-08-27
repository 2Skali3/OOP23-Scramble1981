package scramble.model.scores;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;

import scramble.utility.Constants;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * This class handles the score board. It creates a list to be display in the
 * start menu.
 */
public class Scores {

    private static final Logger LOG = Logger.getLogger(Scores.class.getName());
    
    private final List<Integer> scoresList;

    /**
     * Class constructor.
     */
    public Scores() {
        scoresList = new ArrayList<>(Constants.MAX_N_SCORES); // Initialize with zeros

        // Read scores from JSON file
        try (Reader reader = new InputStreamReader(
                Scores.class.getResourceAsStream(Constants.SCORES_FILE_PATH), "UTF-8");) {

            final Gson gson = new Gson();
            final ScoreData loadedScores = gson.fromJson(reader, ScoreData.class);
            if (loadedScores != null) {
                for (final int i : loadedScores.getScores()) {
                    scoresList.add(i);
                }
            }
            reader.close();
        } catch (IOException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
    }

    /**
     * Adds a score to the scoreboard.
     * 
     * @param score the new score
     */
    public void addScore(final int score) {
        for (int i = 0; i < Constants.MAX_N_SCORES; i++) {
            if (score > scoresList.get(i)) {
                scoresList.set(i, score);
            }
        }
    }

    /**
     * Getter for the score's list. SpotBgs warning suppressed since it returns a
     * std class.
     * 
     * @return the score's list
     */
    public List<Integer> getScoresList() {
        return new ArrayList<>(scoresList);
    }

    /**
     * This method clears the scoreboard.
     */
    public void resetScores() {
        scoresList.clear();
    }

    // Nested class for json data handling
    private static final class ScoreData {
        private final List<Integer> scores;

        private ScoreData() {
            this.scores = new ArrayList<>();
        }

        private List<Integer> getScores() {
            return Objects.nonNull(scores) ? new ArrayList<>(scores) : new ArrayList<>();
        }
    }
}
