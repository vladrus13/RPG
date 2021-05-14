package ru.vladrus13.rpg.basic.event.world;

public class WorldEventChange extends WorldEvent {
    public final ChangeWorldFrame type;

    public WorldEventChange(ChangeWorldFrame type) {
        this.type = type;
    }

    public enum ChangeWorldFrame {
        QUICK_MENU, GAME_OVER, REGION
    }
}
