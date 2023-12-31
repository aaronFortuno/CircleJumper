package net.estemon.studio.config;

public class GameConfig {

    // constants
    public static final float WIDTH = 600f; // px
    public static final float HEIGHT = 800f; // px

    public static final float HUD_WIDTH = 600f;
    public static final float HUD_HEIGHT = 800f;

    public static final float WORLD_WIDTH = 16f;
    public static final float WORLD_HEIGHT = 24f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;
    
    public static final int CELL_SIZE = 1;

    public static final float PLANET_SIZE = 9f;
    public static final float PLANET_HALF_SIZE = PLANET_SIZE / 2f;

    public static final float START_ANGLE = -90f;
    public static final float ANGLE_ADJUSTER = 5f;
    public static final float MIN_ANGLE_DISTANCE = 60f;

    public static final float PLAYER_SIZE = 1f;
    public static final float PLAYER_HALF_SIZE = PLAYER_SIZE / 2f;

    public static final float PLAYER_START_ANG_SPEED = 45f;

    public static final float PLAYER_MAX_SPEED = 2f;
    public static final float PLAYER_GRAVITY = 9.8f;
    public static final float GAME_GRAVITY = PLAYER_GRAVITY * 2f; // -y acceleration

    public static final float COIN_SIZE = 1f;
    public static final float HALF_COIN_SIZE = COIN_SIZE / 2f;
    public static final float COIN_SPAWN_TIME = 1.25f;
    public static final int MAX_COINS = 2;
    public static final int COIN_SCORE = 10;

    public static final float OBSTACLE_SIZE = 1f;
    public static final float HALF_OBSTACLE_SIZE = OBSTACLE_SIZE / 2f;
    public static final float OBSTACLE_SPAWN_TIME = 0.75f;
    public static final int MIN_OBSTACLES = 2;
    public static final int MAX_OBSTACLES = 4; // MathUtils.random() is exclusive, max will be 3
    public static final int OBSTACLE_SCORE = 5;

    public static final float START_WAIT_TIME = 3f;

    public static final float FLOATING_DURATION = 0.75f;

    private GameConfig() {} // not instantiable
}
