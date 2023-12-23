package net.estemon.studio.screen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.config.GameConfig;
import net.estemon.studio.entity.Planet;
import net.estemon.studio.entity.Player;
import net.estemon.utils.ViewportUtils;
import net.estemon.utils.debug.DebugCameraController;

public class GameRenderer implements Disposable {

    // attributes
    private final GameController controller;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private DebugCameraController debugCameraController;

    // constructors
    public GameRenderer(GameController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    // public methods
    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        renderDebug();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // private methods
    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer, GameConfig.CELL_SIZE);

        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();

        renderer.end();
    }

    private void drawDebug() {
        Color oldColor = renderer.getColor();

        // planet
        Planet planet = controller.getPlanet();
        Circle planetBounds = planet.getBounds();
        renderer.setColor(Color.RED);
        renderer.circle(planetBounds.x, planetBounds.y, planetBounds.radius, 60);

        // player
        Player player = controller.getPlayer();
        Rectangle playerBounds = player.getBounds();
        renderer.setColor(Color.CORAL);
        renderer.rect(playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height);

        renderer.setColor(oldColor);
    }
}
