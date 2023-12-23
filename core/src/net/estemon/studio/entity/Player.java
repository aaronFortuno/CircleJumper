package net.estemon.studio.entity;

import com.badlogic.gdx.math.MathUtils;

import net.estemon.studio.config.GameConfig;
import net.estemon.utils.entity.EntityBase;

public class Player extends EntityBase {

    // attributes
    private float angleDeg = GameConfig.START_ANGLE;
    private float angleDegSpeed = GameConfig.PLAYER_START_ANG_SPEED;
    private float yPositionFromSurface = 0f;
    private float acceleration = GameConfig.PLAYER_GRAVITY;
    private PlayerState state = PlayerState.WALKING;

    // constructors
    public Player() {
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

    // public methods
    public void update(float delta) {
        if (state.isJumping() || state.isFalling()) {
            yPositionFromSurface += acceleration * delta;
            acceleration -= GameConfig.GAME_GRAVITY * delta;

            fall();

            if (yPositionFromSurface <= 0) {
                yPositionFromSurface = 0;
                walk();
                acceleration = GameConfig.PLAYER_GRAVITY;
            }
        }

        angleDeg += angleDegSpeed * delta;
        angleDeg = angleDeg % 360;

        float radius = GameConfig.PLANET_HALF_SIZE + yPositionFromSurface;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);
    }

    public float getAngleDeg() {
        return angleDeg;
    }

    public void jump() {
        state = PlayerState.JUMPING;
    }

    public boolean isWalking() {
        return state.isWalking();
    }

    public void reset() {
        angleDeg = GameConfig.START_ANGLE;
    }

    // private methods
    private void fall() {
        state = PlayerState.FALLING;
    }

    private void walk() {
        state = PlayerState.WALKING;
    }
}
