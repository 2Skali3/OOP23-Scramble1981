package scramble.model.scores;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;

import scramble.utility.Constants;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.nio.charset.StandardCharsets;

/**
 * This class handles the score board. It creates a list to be display in the
 * start menu.
 */
public class Scores {

    private static final Logger LOG = Logger.getLogger(Scores.class.getName());
    private static final String SCORES_FILE_PATH = System.getProperty("user.home") + "/scores.json";
    private static List<Integer> scoresList = new ArrayList<>(Constants.MAX_N_SCORES);
    private static int currentScore;

    /**
     * Class constructor.
     */
    public Scores() {

        // Read scores from JSON file
        final File scoresFile = new File(SCORES_FILE_PATH);
        if (scoresFile.exists()) {
            try (Reader reader = new FileReader(scoresFile, StandardCharsets.UTF_8)) {
                final Gson gson = new Gson();
                final ScoreData loadedScores = gson.fromJson(reader, ScoreData.class);
                if (loadedScores != null) {
                    for (final int i : loadedScores.getScores()) {
                        scoresList.add(i);
                    }
                }
                reader.close();
            } catch (IOException e) {
                LOG.severe("Failed to read scores from file!");
                LOG.severe(e.toString());
            }
        } else {
            // If the file doesn't exist, load default scores from the resources
            loadDefaultScores();
        }
    }

    private void loadDefaultScores() {
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
    public static void addScore(final int score) {
        for (int i = 0; i < Constants.MAX_N_SCORES; i++) {
            if (score > scoresList.get(i)) {
                scoresList.add(i, score); // Insert the score at the right place
                if (scoresList.size() > Constants.MAX_N_SCORES) {
                    scoresList.remove(scoresList.size() - 1); // Remove the last element if the list exceeds the limit
                }
                break;
            }
        }

        try (FileWriter writer = new FileWriter(SCORES_FILE_PATH, StandardCharsets.UTF_8)) {
            final Gson gson = new Gson();
            final ScoreData updatedScores = new ScoreData(scoresList);
            gson.toJson(updatedScores, writer);
            writer.flush();
        } catch (IOException e) {
            LOG.severe("Failed to save scores!");
            LOG.severe(e.toString());
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

        private ScoreData(final List<Integer> scores) {
            this.scores = scores;
        }

        private List<Integer> getScores() {
            return Objects.nonNull(scores) ? new ArrayList<>(scores) : new ArrayList<>();
        }
    }

    /**
     * Getter for current score.
     *
     * @return an int
     */
    public static int getCurrentScore() {
        return currentScore;
    }

    /**
     * Increments the current score.
     *
     * @param enemyPoints the amount of point to add
     */
    public static void incrementCurrentScore(final int enemyPoints) {
        currentScore += enemyPoints;
    }

    /** Resets current score. */
    public static void resetCurrentScore() {
        currentScore = 0;
    }

}
