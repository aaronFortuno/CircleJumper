package net.estemon.studio.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;

import net.estemon.studio.config.GameConfig;
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

    private boolean update = false;

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
        if (!update) {
            player.update(delta);
            // update = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.isWalking()) {
            player.jump();
        }
    }

    public Planet getPlanet() {
        return planet;
    }

    public Player getPlayer() {
        return player;
    }
}
