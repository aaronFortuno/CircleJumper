package net.estemon.studio.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

import net.estemon.studio.config.GameConfig;
import net.estemon.utils.entity.EntityBase;

public class Obstacle extends EntityBase implements Pool.Poolable {

    // attributes
    private float angleDeg;
    private Rectangle sensor = new Rectangle();
    private float sensorAngleDeg;
    private float radius = GameConfig.PLANET_HALF_SIZE - GameConfig.OBSTACLE_SIZE;

    // constructors

    public Obstacle() {
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);

    }

    // public methods
    public void update(float delta) {
        // obstacle
        // float radius = GameConfig.PLANET_HALF_SIZE;
        if (radius < GameConfig.PLANET_HALF_SIZE) {
            radius += delta;

            float originX = GameConfig.WORLD_CENTER_X;
            float originY = GameConfig.WORLD_CENTER_Y;

            float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
            float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

            setPosition(newX, newY);

            // sensor
            float sensorX = originX + MathUtils.cosDeg(-sensorAngleDeg) * radius;
            float sensorY = originY + MathUtils.sinDeg(-sensorAngleDeg) * radius;

            sensor.set(sensorX, sensorY, getWidth(), getHeight());
        }

    }

    public void setAngleDeg(float value) {
        angleDeg = value % 360f;
        sensorAngleDeg = angleDeg + 20f;
    }


    public float getAngleDeg() {
        return angleDeg;
    }

    public float getSensorAngleDeg() {
        return sensorAngleDeg;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    @Override
    public void reset() {
        radius = GameConfig.PLANET_HALF_SIZE - GameConfig.OBSTACLE_SIZE;
    }
}
