package ru.vladrus13.game.world;

import ru.vladrus13.core.basic.Drawn;
import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.utils.Writer;
import ru.vladrus13.game.basic.returned.ReturnEvent;
import ru.vladrus13.game.world.region.Region;
import ru.vladrus13.game.world.region.RegionFactory;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

public class World extends UpdatedFrame {

    Logger logger = Logger.getLogger(World.class.getName());

    public World(int width, int height) {
        super(new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), new ArrayList<>(), null);
        Region region = null;
        try {
            region = RegionFactory.getRegion("01", this);
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        frames.add(region);
        setFocused(region);
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        for (Drawn drawn : frames) {
            drawn.draw(graphics);
        }
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return focused.keyPressed(e);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return focused.mousePressed(e);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        for (Frame frame : frames) {
            if (frame instanceof UpdatedFrame) {
                ((UpdatedFrame) frame).update(delay);
            }
        }
    }
}
