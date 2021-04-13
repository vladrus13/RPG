package ru.vladrus13.rpg.world;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;

import java.util.logging.Logger;

public abstract class World extends UpdatedFrame {

    private final static Logger logger = Logger.getLogger(World.class.getName());

    /**
     * Standard frame constructor
     *
     * @param name   system name of frame
     * @param start  start position of frame
     * @param size   frame size
     * @param parent parent frame
     */
    public World(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
    }

    public abstract void invokeWorldEvent(WorldEvent event);
}
