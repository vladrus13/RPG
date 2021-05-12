package ru.vladrus13.rpg.world;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.Game;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.basic.event.world.WorldEventDrawing;
import ru.vladrus13.rpg.basic.event.world.WorldEventGameOver;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.saves.Save;
import ru.vladrus13.rpg.saves.SaveHolder;
import ru.vladrus13.rpg.world.factory.RegionFactory;
import ru.vladrus13.rpg.world.quickmenu.GameOver;
import ru.vladrus13.rpg.world.quickmenu.QuickMenu;
import ru.vladrus13.rpg.world.region.Region;
import ru.vladrus13.rpg.world.region.WarZone;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class World extends UpdatedFrame {

    protected final static Logger logger = Logger.getLogger(World.class.getName());
    protected Game game;
    protected QuickMenu quickMenu;
    protected Region region;
    protected GameOver gameOver;

    public World(int width, int height, Game game) {
        super("world", new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), null);
        SaveHolder.loadQuickSave();
        init(game);
    }

    public void init(Game game) {
        SaveHolder.loadQuickSave();
        try {
            Integer floor = SaveHolder.save.getInt("floor");
            region = RegionFactory.getRegion(floor == null ? 1 : floor, this);
        } catch (AppException e) {
            Writer.printStackTrace(logger, e);
        }
        this.game = game;
        region.setHero();
        addChild(region);
        addFocused(region);
        recalculateChildes();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        for (Frame frame : childes) {
            frame.draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!focused.isEmpty()) {
            focused.getFirst().keyPressed(e);
        } else {
            super.keyPressed(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!focused.isEmpty()) {
            region.mousePressed(e);
        }
    }

    @Override
    public void callEvent(Event event) {
        if (event instanceof WorldEvent) {
            invokeWorldEvent((WorldEvent) event);
        } else {
            game.callEvent(event);
        }
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        for (Frame child : childes) {
            if (child instanceof UpdatedFrame) {
                ((UpdatedFrame) child).update(delay);
            }
        }
    }

    public void invokeWorldEvent(WorldEvent worldEvent) {
        if (worldEvent instanceof WorldEventTeleport) {
            try {
                childes.clear();
                focused.removeFirst();
                region = RegionFactory.getRegion(((WorldEventTeleport) worldEvent).getId(), this);
                region.setHero((WorldEventTeleport) worldEvent);
                addChild(region);
                addFocused(region);
                if (!(region instanceof WarZone)) {
                    SaveHolder.save.set("floor", String.valueOf(region.id));
                }
                SaveHolder.quickSave();
            } catch (AppException e) {
                Writer.printStackTrace(logger, e);
            }
        }
        if (worldEvent instanceof WorldEventDrawing) {
            WorldEventDrawing worldEventDrawing = (WorldEventDrawing) worldEvent;
            if (worldEventDrawing.isRemove) {
                if (worldEventDrawing.drawing) {
                    removeChild(worldEventDrawing.frame);
                }
                if (worldEventDrawing.focused) {
                    try {
                        removeFocused(worldEventDrawing.frame);
                    } catch (AppException e) {
                        Writer.printStackTrace(logger, e);
                    }
                }
            } else {
                if (worldEventDrawing.drawing) {
                    addChild(worldEventDrawing.frame);
                }
                if (worldEventDrawing.focused) {
                    addFocused(worldEventDrawing.frame);
                }
            }
        }
        if (worldEvent instanceof WorldEventGameOver) {
            childes.clear();
            focused.clear();
            gameOver.reload();
            childes.add(gameOver);
        }
    }
}
