package ru.vladrus13.rpg.basic.event.world;

public class WorldEventQuickMenu extends WorldEvent {
    public final boolean isRemove;

    public WorldEventQuickMenu(boolean isRemove) {
        this.isRemove = isRemove;
    }
}
