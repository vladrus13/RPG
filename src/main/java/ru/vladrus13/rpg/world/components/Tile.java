package ru.vladrus13.rpg.world.components;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Image;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.basic.event.Event;

import java.nio.file.Path;

public class Tile extends Image {

    private static final Path path = Path.of("world").resolve("tiles");
    private boolean isWalkable = true;
    private Event onStep;

    public Tile(String name, Point start, Size size, int id, Frame parent) throws GameException {
        super(name, start, size, path.resolve(id + ".png"), parent);
        recalculate();
    }

    public Tile setWalkable(boolean walkable) {
        isWalkable = walkable;
        return this;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public Tile setOnStep(Event event) {
        this.onStep = event;
        return this;
    }

    public Event onStep() {
        return onStep;
    }
}
