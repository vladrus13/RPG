package ru.vladrus13.game.world;

import ru.vladrus13.core.basic.Drawn;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.utils.Writer;
import ru.vladrus13.game.basic.returned.ReturnEvent;
import ru.vladrus13.game.basic.returned.ReturnInt;
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
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public int mousePressed(MouseEvent e) {
        return ReturnInt.NOTHING;
    }

    @Override
    protected void nonCheckingUpdate(long delay) {

    }
}
