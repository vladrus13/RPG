package ru.vladrus13.game.world.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.components.Image;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;

import java.nio.file.Path;

public class Tile extends Image {

    private static final Path path = Path.of("world").resolve("tiles");

    public Tile(Point start, Size size, int id, Frame parent) throws GameException {
        super(start, size, path.resolve(id + ".png"), parent);
    }
}
