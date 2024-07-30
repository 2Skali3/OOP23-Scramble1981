package scramble.model.states;

/**
 * This class handles the game states.
 */
public interface GameStates {

    /**
     * Enum for the game states.
     */
    enum State {
        /**
         * Playing state.
         */
        PLAYING,
        /**
         * Menu state.
         */
        MENU,
        /**
         * GO state.
         */
        GAME_OVER,
        /**
         * Scores state.
         */
        SCOREBOARD
    }

    /**
     * Getter for the current state.
     * 
     * @return the game state
     */
    State getState();

    /**
     * Updates the game's state.
     */
    void upDateState();
}

/*
 * 
 * stati:
 * -playing
 * -menu
 * - game over
 * -scoreboard (forse)
 * 
 * cosa controlla?:
 * -collisioni
 * -movimenti
 * -vite
 * -generazione elementi di gioco
 * -checkpoint?
 * 
 * 
 * 
 * 
 */
