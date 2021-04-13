package ru.vladrus13.rpg;

import ru.vladrus13.jgraphic.App;
import ru.vladrus13.jgraphic.basic.event.Event;

public abstract class Game extends App {
    public Game(int width, int height) {
        super(width, height);
    }

    public abstract void callEvent(Event event);
}
