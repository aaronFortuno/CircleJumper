package net.estemon.studio.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;

import net.estemon.studio.CircleJumperGame;
import net.estemon.utils.GdxUtils;

public class GameScreen extends ScreenAdapter {

    // attributes
    private final CircleJumperGame game;
    private final AssetManager assetManager;

    private GameController controller;
    private GameRenderer renderer;

    // constructors
    public GameScreen(CircleJumperGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    // public methods
    @Override
    public void show() {
        controller = new GameController();
        renderer = new GameRenderer(controller);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
