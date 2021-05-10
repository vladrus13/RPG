package ru.vladrus13.rpg.basic.event.world;

import ru.vladrus13.jgraphic.basic.Frame;

public class WorldEventDrawing extends WorldEvent {
    public final Frame frame;
    public final boolean focused;
    public final boolean drawing;
    public final boolean isRemove;

    public WorldEventDrawing(Frame frame, boolean focused, boolean drawing, boolean isRemove) {
        this.frame = frame;
        this.focused = focused;
        this.drawing = drawing;
        this.isRemove = isRemove;
    }
}
