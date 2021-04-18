package game;

import game.actors.impl.Hero;
import game.regions.RegionFactoryImpl;
import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.Game;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.saves.SaveHolder;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.region.Region;
import ru.vladrus13.rpg.world.region.WarZone;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.util.logging.Logger;

public class WorldImpl extends World {

    private final static Logger logger = Logger.getLogger(World.class.getName());
    private final Hero hero;
    private final Game game;
    private Region region;

    public WorldImpl(int width, int height, Game game) {
        super(width, height);
        SaveHolder.loadQuickSave();
        try {
            Integer floor = SaveHolder.save.getInt("floor");
            region = RegionFactoryImpl.getRegion(floor == null ? 1 : floor, this);
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        this.game = game;
        hero = (Hero) SaveHolder.save.hero;
        hero.setParent(region);
        region.setHero(hero);
        addFocused(region);
        addChild(hero);
        addChild(region);
        recalculateChildes();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        if (region != null) {
            region.draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!focused.isEmpty()) {
            focused.getFirst().keyPressed(e);
        }
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
                hero.teleport(region, ((WorldEventTeleport) event).getPoint(), ((WorldEventTeleport) event).getDirection());
                region.setHero(hero);
                if (!(region instanceof WarZone)) {
                    SaveHolder.save.set("floor", String.valueOf(region.id));
                }
                SaveHolder.quickSave();
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
