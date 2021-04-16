package game.actors;

import game.actors.impl.Hero;
import game.actors.impl.Pirate;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.rpg.resources.ActorResources;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.factory.ActorFactory;
import ru.vladrus13.rpg.world.region.Region;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ActorFactoryImpl {

    private final static int MAX_ID = 1000;

    public static Method[] actors;

    public static void init() {
        try {
            actors = new Method[MAX_ID];
            actors[1] = ActorFactoryImpl.class.getDeclaredMethod("createHero", Point.class, Region.class);
            actors[2] = ActorFactoryImpl.class.getDeclaredMethod("createPirate", Point.class, Region.class);
            ActorFactory.actors = actors;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Hero createHero(Point start, Region region) {
        return new Hero(start, region, ActorResources.getResourcesBundle("1.name"));
    }

    public static Pirate createPirate(Point start, Region region) {
        return new Pirate(start, region, ActorResources.getResourcesBundle("2.name"));
    }
}
