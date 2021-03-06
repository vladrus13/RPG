package ru.vladrus13.core.services;

import ru.vladrus13.game.Game;

public class GameService {
    private static Game game;

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameService.game = game;
    }
}
