package ru.vladrus13.rpg.basic.event.region;

import ru.vladrus13.jgraphic.basic.Frame;

public class RegionEventFocused extends RegionEvent {
    public final Frame focused;
    public final boolean isNew;

    public RegionEventFocused(Frame focused, boolean isNew) {
        this.focused = focused;
        this.isNew = isNew;
    }
}
