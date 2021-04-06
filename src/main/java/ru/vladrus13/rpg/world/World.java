package ru.vladrus13.rpg.world;

import ru.vladrus13.game.actors.impl.Hero;
import ru.vladrus13.game.regions.RegionFactory;
import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.jgraphic.services.AppService;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.Game;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class World extends UpdatedFrame {

    private final static Logger logger = Logger.getLogger(World.class.getName());
    private Region region;
    private final Hero hero;
    private final int tileSize = MainProperty.getInteger("world.region.tileSize");
    private final Game game;

    public World(int width, int height, Game game) {
        super("world", new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), null);
        try {
            region = RegionFactory.getRegion(1, this);
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        this.game = game;
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
    public void keyPressed(KeyEvent e) {
        focused.getFirst().keyPressed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        focused.getFirst().mousePressed(e);
    }

    @Override
    public void callEvent(Event event) {
        game.callEvent(event);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        region.update(delay);
    }

    public void invokeWorldEvent(WorldEvent event) {
        if (event instanceof WorldEventTeleport) {
            try {
                removeFocused(region);
                region = RegionFactory.getRegion(((WorldEventTeleport) event).getId(), this);
                addFocused(region);
                region.setHero(hero);
                hero.teleport(region, ((WorldEventTeleport) event).getPoint(), ((WorldEventTeleport) event).getDirection());
            } catch (GameException e) {
                Writer.printStackTrace(logger, e);
            }
        }
    }
}
