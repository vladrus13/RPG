package ru.vladrus13.rpg.world.items;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.IntEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.jgraphic.resources.ImageLoader;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

/**
 * @author vladrus13 on 24.03.2021
 **/
public class RegionItem extends Frame {
    public final Item item;
    public final BufferedImage bufferedImage;
    public static final Path regionItemsPath = Path.of("world").resolve("items");

    /**
     * Standard constructor for Frame
     *
     * @param start  start position for frame
     * @param region region frame
     */
    public RegionItem(Point start, Region region, Item item) throws GameException {
        super(String.valueOf(item.id), start, new Size(
                MainProperty.getInteger("world.region.tileSize"),
                MainProperty.getInteger("world.region.tileSize"), CoordinatesType.REAL), region);
        this.item = item;
        this.bufferedImage = ImageLoader.load(regionItemsPath.resolve(item.id + ".png"));
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(bufferedImage, start.x, start.y, size.x, size.y);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
