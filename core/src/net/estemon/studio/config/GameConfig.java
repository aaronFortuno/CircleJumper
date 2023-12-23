package net.estemon.studio.config;

public class GameConfig {

    // constants
    public static final float WIDTH = 480f; // px
    public static final float HEIGHT = 800f; // px

    public static final float WORLD_WIDTH = 16f;
    public static final float WORLD_HEIGHT = 24f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;
    
    public static final int CELL_SIZE = 1;

    public static final float PLANET_SIZE = 9f;
    public static final float PLANET_HALF_SIZE = PLANET_SIZE / 2f;

    public static final float PLAYER_SIZE = 1f;
    public static final float PLAYER_HALF_SIZE = PLAYER_SIZE / 2f;

    public static final float PLAYER_START_ANG_SPEED = 45f;
    public static final float PLAYER_START_ANGLE = -90f;

    private GameConfig() {} // not instantiable
}
