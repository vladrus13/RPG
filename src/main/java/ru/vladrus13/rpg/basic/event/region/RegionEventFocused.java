package ru.vladrus13.rpg.basic.event.region;

import ru.vladrus13.jgraphic.basic.Frame;

public class RegionEventFocused extends RegionEvent {
    public final Frame focused;
    public final boolean drawing;
    public final boolean isRemove;

    public RegionEventFocused(Frame focused, boolean drawing, boolean isRemove) {
        this.focused = focused;
        this.drawing = drawing;
        this.isRemove = isRemove;
    }
}
