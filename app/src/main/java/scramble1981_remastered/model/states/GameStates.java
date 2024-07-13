package scramble1981_remastered.model.states;

public interface GameStates {
    enum State {
        PLAYING, MENU, GAME_OVER, SCOREBOARD
    };

    State getState();

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