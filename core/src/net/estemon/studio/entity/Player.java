package net.estemon.studio.entity;

import net.estemon.studio.config.GameConfig;
import net.estemon.utils.entity.EntityBase;

public class Player extends EntityBase {


    // constructors
    public Player() {
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }
}
