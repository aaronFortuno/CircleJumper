package net.estemon.studio.screen.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import net.estemon.studio.assets.ButtonStyleNames;
import net.estemon.studio.assets.RegionNames;
import net.estemon.studio.common.GameManager;

public class GameOverOverlay extends Table {

    // attributes
    private final OverlayCallback callback;
    private final Skin skin;

    private Label scoreLabel;
    private Label highScoreLabel;

    // constructors
    public GameOverOverlay(Skin skin, OverlayCallback callback) {
        super(skin);
        this.callback = callback;
        this.skin = skin;
        init();
    }

    private void init() {
        defaults().pad(20);

        Image gameOverImage = new Image(skin, RegionNames.GAME_OVER);

        // score table
        Table scoreTable = new Table(skin);
        scoreTable.defaults().pad(20);
        scoreTable.setBackground(RegionNames.PANEL);

        scoreTable.add("SCORE: ").row();
        scoreLabel = new Label("", skin);
        scoreTable.add(scoreLabel).row();

        scoreTable.add("BEST: ").row();
        highScoreLabel = new Label("", skin);
        scoreTable.add(highScoreLabel).row();

        scoreTable.center();

        // button table
        Table buttonTable = new Table();
        buttonTable.bottom();
        ImageButton restartButton = new ImageButton(skin, ButtonStyleNames.RESTART);
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                callback.ready();
            }
        });

        ImageButton homeButton = new ImageButton(skin, ButtonStyleNames.HOME);
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                callback.home();
            }
        });

        buttonTable.add(restartButton).left().bottom().expandX();
        buttonTable.add(scoreTable).center().expandX();
        buttonTable.add(homeButton).right().bottom().expandX();

        add(gameOverImage).top().row();
        add(buttonTable).grow().center().row();

        center();
        setFillParent(true);
        pack();

        updateLabels();
    }

    public void updateLabels() {
        scoreLabel.setText("" + GameManager.INSTANCE.getScore());
        highScoreLabel.setText("" + GameManager.INSTANCE.getHighScore());
    }
}
