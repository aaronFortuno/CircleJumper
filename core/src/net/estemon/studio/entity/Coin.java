package net.estemon.studio.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;

import net.estemon.studio.config.GameConfig;
import net.estemon.utils.entity.EntityBase;

public class Coin extends EntityBase implements Pool.Poolable {

    // attributes
    private float angleDeg;

    // constructors
    public Coin() {
        setSize(GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
    }

    // public methods
    public void setAngleDeg(float value) {
        angleDeg = value % 360f;

        float radius = GameConfig.PLANET_HALF_SIZE;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);
    }

    @Override
    public void reset() {

    }

    public float getAngleDeg() {
        return angleDeg;
    }
}
