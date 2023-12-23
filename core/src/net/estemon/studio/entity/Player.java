package net.estemon.studio.entity;

import com.badlogic.gdx.math.MathUtils;

import net.estemon.studio.config.GameConfig;
import net.estemon.utils.entity.EntityBase;

public class Player extends EntityBase {

    // attributes
    private float angleDeg = GameConfig.PLAYER_START_ANGLE;
    private float angleDegSpeed = GameConfig.PLAYER_START_ANG_SPEED;

    // constructors
    public Player() {
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

    // public methods
    public void update(float delta) {
        angleDeg += angleDegSpeed * delta;
        angleDeg = angleDeg % 360;

        float radius = GameConfig.PLANET_HALF_SIZE;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);
    }

    public float getAngleDeg() {
        return angleDeg;
    }
}
