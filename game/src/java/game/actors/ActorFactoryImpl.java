package game.actors;

import game.actors.impl.Hero;
import game.actors.impl.Pirate;
import game.actors.impl.enemies.Dummy;
import game.actors.impl.enemies.Goblin;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.resources.ActorResources;
import ru.vladrus13.rpg.world.region.Region;
import ru.vladrus13.rpg.world.region.WarZone;

import java.lang.reflect.Method;

import static ru.vladrus13.rpg.world.factory.ActorFactory.actors;

public class ActorFactoryImpl {

    private final static int MAX_ID = 1000;

    public static void init() {
        try {
            actors = new Method[MAX_ID];
            actors[1] = ActorFactoryImpl.class.getDeclaredMethod("createHero", Point.class, Region.class);
            actors[2] = ActorFactoryImpl.class.getDeclaredMethod("createPirate", Point.class, Region.class);
            actors[10] = ActorFactoryImpl.class.getDeclaredMethod("createDummy", Point.class, Region.class);
            actors[11] = ActorFactoryImpl.class.getDeclaredMethod("createGoblin", Point.class, Region.class);
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

    public static Dummy createDummy(Point start, Region region) {
        return new Dummy(start, ActorResources.getResourcesBundle("10.name"), (WarZone) region);
    }

    public static Goblin createGoblin(Point start, Region region) {
        return new Goblin(start, ActorResources.getResourcesBundle("11.name"), (WarZone) region);
    }
}
