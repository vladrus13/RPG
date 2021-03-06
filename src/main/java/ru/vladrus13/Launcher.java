package ru.vladrus13;

import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.game.Game;
import ru.vladrus13.core.property.MainProperty;

import java.io.IOException;
import java.util.logging.LogManager;

public class Launcher {
    public static void main(String[] args) {
        if (args.length == 0) {
            try {
                LogManager.getLogManager().readConfiguration(Launcher.class.getResourceAsStream("/properties/logging.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainProperty.read();
            try {
                Game game = new Game();
            } catch (GameException e) {
                e.printStackTrace();
            }
        }
    }
}
