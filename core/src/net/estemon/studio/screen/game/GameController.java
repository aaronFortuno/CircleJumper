package net.estemon.studio.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import net.estemon.studio.config.GameConfig;
import net.estemon.studio.entity.Coin;
import net.estemon.studio.entity.Planet;
import net.estemon.studio.entity.Player;

public class GameController {

    // constant
    private static final Logger log = new Logger(GameController.class.getSimpleName(), Logger.DEBUG);

    // attributes
    private Planet planet;
    private Player player;

    private float playerStartX;
    private float playerStartY;

    private final Array<Coin> coins = new Array<Coin>();
    private final Pool<Coin> coinPool = Pools.get(Coin.class, 10);
    private float coinTimer;

    // constructor
    public GameController() {
        init();
    }

    private void init() {
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        playerStartX = GameConfig.WORLD_CENTER_X - GameConfig.PLAYER_HALF_SIZE;
        playerStartY = GameConfig.WORLD_CENTER_Y + GameConfig.PLANET_HALF_SIZE;

        player = new Player();
        player.setPosition(playerStartX, playerStartY);
    }

    // public methods
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.isWalking()) {
            player.jump();
        }

        player.update(delta);
        spawnCoins(delta);
    }

    public Planet getPlanet() {
        return planet;
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Coin> getCoins() {
        return coins;
    }

    // private methods
    private void spawnCoins(float delta) {
        coinTimer += delta;

        if (coins.size >= GameConfig.MAX_COINS) {
            coinTimer = 0f;
            return;
        }

        if (coinTimer >= GameConfig.COIN_SPAWN_TIME) {
            coinTimer = 0f;
            Coin coin = coinPool.obtain();
            float randomAngle = MathUtils.random(360);
            coin.setAngleDeg(randomAngle);
            coins.add(coin);
        }
    }
}
