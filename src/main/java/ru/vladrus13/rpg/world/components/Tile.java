package ru.vladrus13.rpg.world.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Image;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnEvent;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.resources.ImageLoader;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.items.RegionItem;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class Tile extends Frame {

    private static final Path path = Path.of("world").resolve("tiles");
    private boolean isWalkable = true;
    private Event onStep;
    public Actor actor;
    public RegionItem regionItem;
    private final BufferedImage image;

    public Tile(String name, Point start, Size size, int id, Frame parent) throws GameException {
        super(name, start, size, parent);
        image = ImageLoader.load(path.resolve(id + ".png"));
        recalculate();
    }

    public Tile setWalkable(boolean walkable) {
        isWalkable = walkable;
        return this;
    }

    public boolean isWalkable() {
        return isWalkable && actor == null && regionItem == null;
    }

    public Tile setOnStep(Event event) {
        this.onStep = event;
        return this;
    }

    public Event onStep() {
        return onStep;
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(image, start.x, start.y, size.x, size.y);
        if (actor != null) {
            actor.draw(graphics);
        }
        if (regionItem != null) {
            regionItem.draw(graphics);
        }
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return null;
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return null;
    }
}
