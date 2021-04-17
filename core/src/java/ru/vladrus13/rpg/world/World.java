package ru.vladrus13.rpg.world;

import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.saves.Save;
import ru.vladrus13.rpg.saves.SaveHolder;
import ru.vladrus13.rpg.world.region.Region;

import java.util.logging.Logger;

public abstract class World extends UpdatedFrame {

    private final static Logger logger = Logger.getLogger(World.class.getName());

    public World(int width, int height) {
        super("world", new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), null);
    }

    public World(int width, int height, Save save) {
        super("world", new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), null);
        SaveHolder.setSave(save);
    }

    public abstract void invokeWorldEvent(WorldEvent event);

    public abstract Region getCurrentRegion();
}