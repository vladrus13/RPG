package ru.vladrus13.game.basic.event.region;

import ru.vladrus13.core.basic.Frame;

public class RegionEventFocused extends RegionEvent {
    private final Frame focused;

    public RegionEventFocused(Frame focused) {
        this.focused = focused;
    }

    public Frame getFocused() {
        return focused;
    }
}
