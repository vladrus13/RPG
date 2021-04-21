package ru.vladrus13.rpg.basic.event.region;

import ru.vladrus13.jgraphic.basic.Frame;

public class RegionEventDrawing extends RegionEvent {
    public final boolean isFocused;
    public final Frame frame;
    public final boolean drawing;
    public final boolean isRemove;

    public RegionEventDrawing(Frame frame, boolean drawing, boolean isRemove, boolean isFocused) {
        this.frame = frame;
        this.drawing = drawing;
        this.isRemove = isRemove;
        this.isFocused = isFocused;
    }
}
