package net.estemon.studio.screen.menu;

import com.badlogic.gdx.Gdx;
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

public class MenuOverlay extends Table {

    // attributes
    private final OverlayCallback callback;
    private Label highScoreLabel;

    // constructors
    public MenuOverlay(Skin skin, OverlayCallback callback) {
        super(skin);
        this.callback = callback;
        init();
    }

    private void init() {
        defaults().pad(20);

        Table logoTable = new Table();
        logoTable.top();
        Image logoImage = new Image(getSkin(), RegionNames.LOGO);
        logoTable.add(logoImage);

        Table buttonTable = new Table();
        buttonTable.bottom();
        ImageButton playButton = new ImageButton(getSkin(), ButtonStyleNames.PLAY);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                callback.ready();
            }
        });

        ImageButton quitButton = new ImageButton(getSkin(), ButtonStyleNames.QUIT);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        Table scoreTable = new Table(getSkin());
        scoreTable.add("BEST: ").row();
        highScoreLabel = new Label("", getSkin());
        updateLabel();
        scoreTable.add(highScoreLabel);

        buttonTable.add(playButton).center().expandX();
        buttonTable.add(scoreTable).center().expandX();
        buttonTable.add(quitButton).center().expandX();

        add(logoTable).top().row();
        add(buttonTable).grow().center().row();

        center();
        setFillParent(true);
        pack();
    }

    public void updateLabel() {
        highScoreLabel.setText("" + GameManager.INSTANCE.getHighScore());
    }
}
