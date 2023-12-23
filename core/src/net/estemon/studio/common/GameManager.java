package net.estemon.studio.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import net.estemon.studio.CircleJumperGame;

public class GameManager {

    // constants
    public static final GameManager INSTANCE = new GameManager();
    private static final String HIGH_SCORE_KEY = "highScore";

    // attributes
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;

    private final Preferences PREFS;


    // constructors
    private GameManager() {
        PREFS = Gdx.app.getPreferences(CircleJumperGame.class.getSimpleName());
        highScore = PREFS.getInteger(HIGH_SCORE_KEY, 0);
        displayHighScore = highScore;
    }

    // public methods
    public void updateHighScore() {
        if (score < highScore) {
            return;
        }

        highScore = score;
        PREFS.putInteger(HIGH_SCORE_KEY, highScore);
        PREFS.flush();
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }

    public void reset() {
        score = 0;
        displayScore = 0;
    }

    public void addScore(int amount) {
        score += amount;

        if (score > highScore) {
            highScore = score;
        }
    }

    public void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (int) (100 * delta));
        }

        if (displayHighScore < highScore) {
            displayHighScore = Math.min(highScore, displayHighScore + (int) (100 * delta));
        }
    }
}
