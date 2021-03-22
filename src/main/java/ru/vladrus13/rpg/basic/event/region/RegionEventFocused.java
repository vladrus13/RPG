package ru.vladrus13.rpg.basic.event.region;

import ru.vladrus13.jgraphic.basic.Frame;

public class RegionEventFocused extends RegionEvent {
    private final Frame focused;

    public RegionEventFocused(Frame focused) {
        this.focused = focused;
    }

    public Frame getFocused() {
        return focused;
    }
}
