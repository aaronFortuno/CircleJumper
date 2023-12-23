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

    private GameConfig() {} // not instantiable
}
