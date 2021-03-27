package ru.vladrus13.rpg.basic.event.region;

import ru.vladrus13.jgraphic.basic.Frame;

public class RegionEventFocused extends RegionEvent {
    public final Frame focused;
    public final boolean isNew;
    public final boolean isRemove;

    public RegionEventFocused(Frame focused, boolean isNew, boolean isRemove) {
        this.focused = focused;
        this.isNew = isNew;
        this.isRemove = isRemove;
    }
}
