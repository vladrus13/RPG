package ru.vladrus13.rpg.world.factory;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class ActorFactory {

    private final static Logger logger = Logger.getLogger(ItemFactory.class.getName());

    public static Method[] actors;

    public static Actor createActor(int id, Point start, Region region) {
        try {
            return (Actor) actors[id].invoke(ActorFactory.class, start, region);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Writer.printStackTrace(logger, e);
        }
        return null;
    }
}
