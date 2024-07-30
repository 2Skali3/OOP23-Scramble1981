package scramble.model.map;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Static class used to store the lists with the level's
 * landscape data. Every element is an hexa number that gets later decoded
 * for assembling the game map.
 */
public final class LandscapeUtil {

        private static final Logger LOG = Logger.getLogger(LandscapeUtil.class.getName());

        private LandscapeUtil() {
        }

        private static List<String> loadStage(final String fileName) throws IOException {

                final InputStream inputStream = LandscapeUtil.class.getResourceAsStream(fileName);

                // Verifica se il file è stato trovato
                if (inputStream == null) {
                        throw new IOException("File " + fileName + " non trovato!");
                }

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
                        return reader.lines().flatMap(line -> Arrays.stream(line.split(",")))
                                        .map(String::trim)
                                        .toList();
                } catch (IOException e) {
                        LOG.severe("Ops!");
                        LOG.severe(e.toString());
                }

                return new ArrayList<String>();
        }

        /**
         * Loads the stage from the files in resources.
         * 
         * @return the landscape as a list
         */
        public static List<String> getAllStages() {
                final List<String> stages = new ArrayList<>();

                try {
                        stages.addAll(loadStage("/map/levels/stageOne.txt"));
                        stages.addAll(loadStage("/map/levels/stageTwo.txt"));
                        stages.addAll(loadStage("/map/levels/stageThree.txt"));
                        stages.addAll(loadStage("/map/levels/stageFour.txt"));
                        stages.addAll(loadStage("/map/levels/stageFive.txt"));
                        stages.addAll(loadStage("/map/levels/stageSix.txt"));
                } catch (IOException e) {
                        LOG.severe("Ops!");
                        LOG.severe(e.toString());
                }

                return stages;
        }
}
