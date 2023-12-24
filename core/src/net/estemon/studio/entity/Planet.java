package net.estemon.studio.entity;

import com.badlogic.gdx.math.Circle;

import net.estemon.studio.config.GameConfig;

public class Planet {

    // attributes
    private float x;
    private float y;

    private float width = 1f;
    private float height = 1f;

    private Circle bounds;

    // constructor
    public Planet() {
        bounds = new Circle(x, y, GameConfig.PLANET_HALF_SIZE);
        setSize(GameConfig.PLANET_SIZE, GameConfig.PLANET_SIZE);
    }

    // public methods
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        updateBounds();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        updateBounds();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        updateBounds();
    }

    public Circle getBounds() {
        return bounds;
    }

    // private methods
    private void updateBounds() {
        bounds.setPosition(x, y);
    }
}
