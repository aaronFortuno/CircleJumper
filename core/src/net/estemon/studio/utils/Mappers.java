package net.estemon.studio.utils;

import com.badlogic.ashley.core.ComponentMapper;

import net.estemon.studio.component.BodyPartComponent;
import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.CoinComponent;
import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.DirectionComponent;
import net.estemon.studio.component.MovementComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.component.SnakeComponent;
import net.estemon.studio.component.TextureComponent;
import net.estemon.studio.component.ZOrderComponent;

public final class Mappers {

    private Mappers() {} // not instantiable

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);
    public static final ComponentMapper<SnakeComponent> SNAKE =
            ComponentMapper.getFor(SnakeComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<DirectionComponent> DIRECTION =
            ComponentMapper.getFor(DirectionComponent.class);
    public static final ComponentMapper<CoinComponent> COIN =
            ComponentMapper.getFor(CoinComponent.class);
    public static final ComponentMapper<BodyPartComponent> BODY_PART =
            ComponentMapper.getFor(BodyPartComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<ZOrderComponent> Z_ORDER =
            ComponentMapper.getFor(ZOrderComponent.class);
}
