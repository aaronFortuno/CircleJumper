package net.estemon.studio.entity;

import com.badlogic.gdx.math.MathUtils;

import net.estemon.studio.config.GameConfig;
import net.estemon.utils.entity.EntityBase;

public class Player extends EntityBase {

    // attributes
    private float angleDeg = GameConfig.PLAYER_START_ANGLE;
    private float angleDegSpeed = GameConfig.PLAYER_START_ANG_SPEED;
    private float yPosition = 0f;
    private float acceleration = GameConfig.PLAYER_GRAVITY;
    private PlayerState state = PlayerState.WALKING;

    // constructors
    public Player() {
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

    // public methods
    public void update(float delta) {
        if (state.isJumping() || state.isFalling()) {
            yPosition += acceleration * delta;
            acceleration -= GameConfig.GAME_GRAVITY * delta;

            if (yPosition < 0 && state.isJumping()) {
                fall();
            }

            if (yPosition < 0 && state.isFalling()) {
                yPosition = 0;
                walk();
                acceleration = GameConfig.PLAYER_GRAVITY;
            }
        }

/*        if (state.isJumping()) {
            speed += acceleration * delta;
            acceleration -= 5f * delta;

            // when reached max speed state to falling
            if (speed >= GameConfig.PLAYER_MAX_SPEED || acceleration <= 0) {
                fall();
                acceleration = GameConfig.PLAYER_START_ACC;
            }
        } else if (state.isFalling()) {
            // falling down
            speed -= acceleration * delta;
            acceleration += 5f * delta;

            // when speed is 0 we are walking again
            if (speed <= 0) {
                speed = 0;
                walk();
            }
        }*/

        angleDeg += angleDegSpeed * delta;
        angleDeg = angleDeg % 360;

        float radius = GameConfig.PLANET_HALF_SIZE + yPosition;
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

    // private methods
    private void fall() {
        state = PlayerState.FALLING;
    }

    private void walk() {
        state = PlayerState.WALKING;
    }
}
