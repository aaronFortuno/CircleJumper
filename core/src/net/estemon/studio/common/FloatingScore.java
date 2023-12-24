package net.estemon.studio.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;

import net.estemon.studio.config.GameConfig;

public class FloatingScore implements Pool.Poolable {

    // attributes
    private Color color = Color.CORAL.cpy();

    private int score;
    private float startX;
    private float startY;
    private float x;
    private float y;
    private float timer;


    // constructors
    public FloatingScore() {}

    // public methods
    public void update(float delta) {
        if (isFinished()) {
            return;
        }

        timer += delta;

        float percentage = timer / GameConfig.FLOATING_DURATION;
        float alpha = MathUtils.clamp(1f - percentage, 0f, 1f);

        x = startX;
        y = startY + percentage * 60;

        color.a = alpha;
    }

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
        return x;
    }

    public float getY() {
        return y;
    }

    public String getScoreString() {
        return Integer.toString(score);
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
        x = 0f;
        y = 0f;
        color.a = 1f;
    }
}
