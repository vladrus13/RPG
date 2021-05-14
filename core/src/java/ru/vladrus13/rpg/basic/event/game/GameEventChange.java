package ru.vladrus13.rpg.basic.event.game;

public class GameEventChange extends GameEvent {
    public final ChangeWorldFrame type;

    public GameEventChange(ChangeWorldFrame type) {
        this.type = type;
    }

    public enum ChangeWorldFrame {
        MENU, WORLD
    }
}
