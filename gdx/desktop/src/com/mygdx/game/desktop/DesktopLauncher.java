package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import GamDev.GamDev;

public class DesktopLauncher {

    public static void main(String[] arg) {
        int nHeight = 700;
        int nWidth = 1000;
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = nHeight;
        config.width = nWidth;
        new LwjglApplication(new GamDev(), config);
    }
}
