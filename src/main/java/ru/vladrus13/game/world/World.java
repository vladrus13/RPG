package ru.vladrus13.game.world;

import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.core.utils.Writer;
import ru.vladrus13.game.basic.event.world.WorldEvent;
import ru.vladrus13.game.basic.event.world.WorldEventTeleport;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.world.actors.Hero;
import ru.vladrus13.game.world.region.Region;
import ru.vladrus13.game.world.region.RegionFactory;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.logging.Logger;

public class World extends UpdatedFrame {

    private final static Logger logger = Logger.getLogger(World.class.getName());
    private Region region;
    private final Hero hero;
    private final int tileSize = MainProperty.getInteger("world.region.tileSize");

    public World(int width, int height) {
        super(new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), Collections.emptyList(), null);
        try {
            region = RegionFactory.getRegion(1, this);
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        hero = new Hero(new Point(tileSize, tileSize, CoordinatesType.REAL), region);
        region.setHero(hero);
        addFocused(region);
        recalculate();
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
