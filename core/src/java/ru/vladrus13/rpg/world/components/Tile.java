package ru.vladrus13.rpg.world.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.resources.ImageLoader;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.RegionItem;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedList;

public class Tile extends Frame {

    private static final Path path = Path.of("world").resolve("tiles");
    private final BufferedImage image;
    public Actor actor;
    public LinkedList<RegionItem> regionItems = new LinkedList<>();
    private boolean isWalkable = true;
    private Event onStep;

    public Tile(String name, Point start, Size size, int id, Frame parent) throws GameException {
        super(name, start, size, parent);
        image = ImageLoader.load(path.resolve(id + ".png"));
        recalculate();
    }

    public boolean isWalkable() {
        return isWalkable && (actor == null || actor.untouchable);
    }

    public Tile setWalkable(boolean walkable) {
        isWalkable = walkable;
        return this;
    }

    public void setOnStep(Event event) {
        this.onStep = event;
    }

    public Event onStep() {
        return onStep;
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(image, start.x, start.y, size.x, size.y);
    }

    public void drawIn(Graphics graphics) {
        if (actor != null) {
            actor.draw(graphics);
        }
        for (RegionItem regionItem : regionItems) {
            regionItem.draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
