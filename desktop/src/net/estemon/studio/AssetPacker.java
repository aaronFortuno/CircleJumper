package net.estemon.studio;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    private static final String RAW_ASSETS_PATH = "assets/raw";
    private static final String FINAL_ASSETS_PATH = "assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.flattenPaths = true;

        TexturePacker.process(settings,
                RAW_ASSETS_PATH + "/gameplay",
                FINAL_ASSETS_PATH + "/gameplay",
                "gameplay");
    }
}
