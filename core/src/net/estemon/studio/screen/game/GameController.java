package net.estemon.studio.screen.game;

import com.badlogic.gdx.utils.Logger;

import net.estemon.studio.config.GameConfig;
import net.estemon.studio.entity.Planet;

public class GameController {

    // constant
    private static final Logger log = new Logger(GameController.class.getSimpleName(), Logger.DEBUG);

    // controller
    private Planet planet;

    // constructor
    public GameController() {
        init();
    }

    private void init() {
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    // public methods
    public void update(float delta) {

    }

    public Planet getPlanet() {
        return planet;
    }
}
