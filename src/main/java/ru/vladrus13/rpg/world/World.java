package ru.vladrus13.rpg.world;

import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnEvent;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.world.actors.Hero;
import ru.vladrus13.rpg.world.region.Region;
import ru.vladrus13.rpg.world.region.RegionFactory;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class World extends UpdatedFrame {

    private final static Logger logger = Logger.getLogger(World.class.getName());
    private Region region;
    private final Hero hero;
    private final int tileSize = MainProperty.getInteger("world.region.tileSize");

    public World(int width, int height) {
        super("world", new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), null);
        try {
            region = RegionFactory.getRegion(1, this);
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        hero = new Hero(new Point(tileSize, tileSize, CoordinatesType.REAL), region);
        region.setHero(hero);
        addFocused(region);
        addChild(hero);
        addChild(region);
        recalculateChildes();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        region.draw(graphics);
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return focused.getFirst().keyPressed(e);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return focused.getFirst().mousePressed(e);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        region.update(delay);
    }

    public void invokeWorldEvent(WorldEvent event) {
        if (event instanceof WorldEventTeleport) {
            try {
                region = RegionFactory.getRegion(((WorldEventTeleport) event).getId(), this);
                region.setHero(hero);
                hero.teleport(region, ((WorldEventTeleport) event).getPoint(), ((WorldEventTeleport) event).getDirection());
            } catch (GameException e) {
                Writer.printStackTrace(logger, e);
            }
        }
    }
}
