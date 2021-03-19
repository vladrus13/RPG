package ru.vladrus13.core.services;

import ru.vladrus13.game.Game;

/**
 * Game service. Contain game and all properties
 */
public class GameService {
    /**
     * Game object
     */
    private static Game game;

    /**
     * @return game
     */
    public static Game getGame() {
        return game;
    }

    /**
     * @param game game
     */
    public static void setGame(Game game) {
        GameService.game = game;
    }
}
