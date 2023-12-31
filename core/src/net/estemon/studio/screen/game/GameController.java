package net.estemon.studio.screen.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import net.estemon.studio.common.FloatingScore;
import net.estemon.studio.common.GameManager;
import net.estemon.studio.common.GameState;
import net.estemon.studio.common.SoundListener;
import net.estemon.studio.config.GameConfig;
import net.estemon.studio.entity.Coin;
import net.estemon.studio.entity.Obstacle;
import net.estemon.studio.entity.Planet;
import net.estemon.studio.entity.Player;
import net.estemon.studio.screen.menu.OverlayCallback;

public class GameController {

    // constant
    private static final Logger log = new Logger(GameController.class.getSimpleName(), Logger.DEBUG);

    // attributes
    private final SoundListener listener;

    private Planet planet;
    private Player player;

    private float playerStartX;
    private float playerStartY;

    private final Array<Coin> coins = new Array<Coin>();
    private final Pool<Coin> coinPool = Pools.get(Coin.class, 10);
    private float coinTimer;

    private final Array<Obstacle> obstacles = new Array<Obstacle>();
    private final Pool<Obstacle> obstaclePool = Pools.get(Obstacle.class, 10);
    private float obstacleTimer;

    private float startWaitTimer = GameConfig.START_WAIT_TIME;
    private float animationTime;

    private GameState gameState = GameState.MENU;
    private OverlayCallback callback;

    private final Array<FloatingScore> floatingScores = new Array<>();
    private Pool<FloatingScore> floatingScorePool = Pools.get(FloatingScore.class);

    // constructor
    public GameController(SoundListener listener) {
        this.listener = listener;
        init();
    }

    private void init() {
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        playerStartX = GameConfig.WORLD_CENTER_X - GameConfig.PLAYER_HALF_SIZE;
        playerStartY = GameConfig.WORLD_CENTER_Y + GameConfig.PLANET_HALF_SIZE;

        player = new Player();
        player.setPosition(playerStartX, playerStartY);

        callback = new OverlayCallback() {
            @Override
            public void home() {
                gameState = GameState.MENU;
            }

            @Override
            public void ready() {
                restart();
                gameState = GameState.READY;
            }
        };
    }

    // public methods
    public void update(float delta) {
        if (gameState.isReady() && startWaitTimer > 0) {
            startWaitTimer -= delta;

            if (startWaitTimer <= 0) {
                gameState = GameState.PLAYING;
            }
        }

        if (!gameState.isPlaying()) {
            return;
        }

        animationTime += delta;

        GameManager.INSTANCE.updateDisplayScore(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.isWalking()) {
            listener.jump();
            player.jump();
        }

        player.update(delta);

        for (Obstacle obstacle : obstacles) {
            obstacle.update(delta);
        }

        for(int i = 0; i < floatingScores.size; i++) {
            FloatingScore floatingScore = floatingScores.get(i);
            floatingScore.update(delta);

            if (floatingScore.isFinished()) {
                floatingScorePool.free(floatingScore);
                floatingScores.removeIndex(i);
            }
        }

        for (Coin coin : coins) {
            coin.update(delta);
        }

        spawnObstacles(delta);
        spawnCoins(delta);

        checkCollision();
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

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public Array<FloatingScore> getFloatingScores() {
        return floatingScores;
    }

    public float getStartWaitTimer() {
        return startWaitTimer;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public GameState getGameState() {
        return gameState;
    }

    public OverlayCallback getCallback() {
        return callback;
    }

    public void restart() {
        coinPool.freeAll(coins);
        coins.clear();

        obstaclePool.freeAll(obstacles);
        obstacles.clear();

        floatingScorePool.freeAll(floatingScores);
        floatingScores.clear();

        player.reset();
        player.setPosition(playerStartX, playerStartY);

        GameManager.INSTANCE.updateHighScore();
        GameManager.INSTANCE.reset();
        startWaitTimer = GameConfig.START_WAIT_TIME;
        animationTime = 0f;
        gameState = GameState.READY;
    }

    // private methods
    private void spawnObstacles(float delta) {
        obstacleTimer += delta;

        if (obstacleTimer < GameConfig.OBSTACLE_SPAWN_TIME) {
            return;
        }

        obstacleTimer = 0f;

        if (obstacles.size == 0) {
            addObstacles();
        }
    }

    private void addObstacles() {
        int count = MathUtils.random(GameConfig.MIN_OBSTACLES, GameConfig.MAX_OBSTACLES);

        for (int i = 0; i < count; i++) {
            float randomAngle = player.getAngleDeg()
                    - i * GameConfig.MIN_ANGLE_DISTANCE
                    - MathUtils.random(60, 80);

            boolean canSpawn = !isObstacleNearBy(randomAngle)
                    && !isCoinNearBy(randomAngle)
                    && !isPlayerNearBy(randomAngle);

            if (canSpawn) {
                Obstacle obstacle = obstaclePool.obtain();
                obstacle.setAngleDeg(randomAngle);
                obstacles.add(obstacle);
            }
        }
    }

    private void spawnCoins(float delta) {
        coinTimer += delta;

        if (coinTimer < GameConfig.COIN_SPAWN_TIME) {
            return;
        }

        coinTimer = 0f;

        if (coins.size == 0) {
            addCoins();
        }
    }

    private void addCoins() {
        int count = MathUtils.random(GameConfig.MAX_COINS);
        for (int i = 0; i < count ; i++) {
            float randomAngle = MathUtils.random(360f);

            boolean canSpawn = !isCoinNearBy(randomAngle)
                    && !isPlayerNearBy(randomAngle);

            if (canSpawn) {
                Coin coin = coinPool.obtain();

                if (isObstacleNearBy(randomAngle)) {
                    coin.setOffset(true);
                }

                coin.setAngleDeg(randomAngle);
                coins.add(coin);
            }
        }
    }

    private boolean isCoinNearBy(float angle) {
        for (Coin coin : coins) {
            float angleDeg = coin.getAngleDeg();
            float diff = Math.abs(Math.abs(angleDeg) - Math.abs(angle));

            if (diff < GameConfig.MIN_ANGLE_DISTANCE) {
                return true;
            }
        }

        return false;
    }

    private boolean isPlayerNearBy(float angle) {
        float playerDiff = Math.abs(Math.abs(player.getAngleDeg()) - Math.abs(angle));

        if (playerDiff < GameConfig.MIN_ANGLE_DISTANCE) {
            return true;
        }

        return false;
    }

    private boolean isObstacleNearBy(float angle) {
        for (Obstacle obstacle : obstacles) {
            float angleDeg = obstacle.getAngleDeg();
            float diff = Math.abs(Math.abs(angleDeg) - Math.abs(angle));

            if (diff < GameConfig.MIN_ANGLE_DISTANCE / 2f) {
                return true;
            }
        }

        return false;
    }

    private void checkCollision() {
        // player <-> coins
        for (Coin coin : coins) {
            if (Intersector.overlaps(player.getBounds(), coin.getBounds())) {
                GameManager.INSTANCE.addScore(GameConfig.COIN_SCORE);
                addFloatingScore(GameConfig.COIN_SCORE);
                coinPool.free(coin);
                coins.removeValue(coin, true);
                listener.hitCoin();
            }
        }

        // player <-> sensor / obstacle
        for (Obstacle obstacle : obstacles) {
            if (Intersector.overlaps(player.getBounds(), obstacle.getSensor())) {
                GameManager.INSTANCE.addScore(GameConfig.OBSTACLE_SCORE);
                addFloatingScore(GameConfig.OBSTACLE_SCORE);
                obstaclePool.free(obstacle);
                obstacles.removeValue(obstacle, true);
            } else if (Intersector.overlaps(player.getBounds(), obstacle.getBounds())) {
                listener.lose();
                gameState = GameState.GAME_OVER;
            }
        }
    }

    private void addFloatingScore(int score) {
        FloatingScore floatingScore = floatingScorePool.obtain();
        floatingScore.setStartPosition(
                GameConfig.HUD_WIDTH / 2f,
                GameConfig.HUD_HEIGHT / 2f
        );
        floatingScore.setScore(score);
        floatingScores.add(floatingScore);
    }
}
