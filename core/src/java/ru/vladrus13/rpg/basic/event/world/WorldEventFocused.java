package ru.vladrus13.rpg.basic.event.world;

import ru.vladrus13.jgraphic.basic.Frame;

public class WorldEventFocused extends WorldEvent {
    public final Frame focused;
    public final boolean drawing;
    public final boolean isRemove;

    public WorldEventFocused(Frame focused, boolean drawing, boolean isRemove) {
        this.focused = focused;
        this.drawing = drawing;
        this.isRemove = isRemove;
    }
}
