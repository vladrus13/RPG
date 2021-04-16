package game.regions;

import game.actors.impl.Hero;
import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.Game;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.saves.SaveHolder;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.factory.ActorFactory;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class WorldImpl extends World {

    private final static Logger logger = Logger.getLogger(World.class.getName());
    private final Hero hero;
    private final Game game;
    private Region region;

    public WorldImpl(int width, int height, Game game) {
        super(width, height);
        try {
            region = RegionFactoryImpl.getRegion(1, this);
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        this.game = game;
        int tileSize = MainProperty.getInteger("world.region.tileSize");
        hero = (Hero) ActorFactory.createActor(1, new Point(tileSize, tileSize, CoordinatesType.REAL), region);
        SaveHolder.setHero(hero);
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
                removeChild(region);
                region = RegionFactoryImpl.getRegion(((WorldEventTeleport) event).getId(), this);
                addChild(region);
                addFocused(region);
                region.setHero(hero);
                hero.teleport(region, ((WorldEventTeleport) event).getPoint(), ((WorldEventTeleport) event).getDirection());
            } catch (GameException e) {
                Writer.printStackTrace(logger, e);
            }
        }
    }

    @Override
    public Region getCurrentRegion() {
        return region;
    }
}
