package net.estemon.studio.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

import net.estemon.studio.config.GameConfig;

public class FloatingScore implements Pool.Poolable {

    // attributes
    private Color color = Color.CORAL;

    private int score;
    private float startX;
    private float startY;
    private float timer;


    // constructors
    public FloatingScore() {}

    // public methods
    public void setStartPosition(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Color getColor() {
        return color;
    }

    public float getX() {
        return startX;
    }

    public float getY() {
        return startY;
    }

    public String getScoreString() {
        return Integer.toString(score);
    }

    public void update(float delta) {
        if (isFinished()) {
            return;
        }

        timer += delta;
    }

    public boolean isFinished() {
        return timer >= GameConfig.FLOATING_DURATION;
    }

    @Override
    public void reset() {
        score = 0;
        startX = 0f;
        startY = 0f;
        timer = 0f;
    }
}
