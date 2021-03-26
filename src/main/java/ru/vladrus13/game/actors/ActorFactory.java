package ru.vladrus13.game.actors;

import ru.vladrus13.game.actors.impl.Pirate;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.jgraphic.resources.ImageLoader;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ActorFactory {

    private final static Logger logger = Logger.getLogger(ActorFactory.class.getName());

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("internationalization.actors", MainProperty.getLocale());

    private final static Path actorsPath = Path.of("world").resolve("actors");

    private final static Map<String, Actor> actors = new HashMap<>();

    public static Map<Direction, BufferedImage> loadActor(String name) {
        Path path = actorsPath.resolve(name);
        Map<Direction, BufferedImage> returned = new HashMap<>();
        try {
            for (Direction direction : Direction.values()) {
                returned.put(direction, ImageLoader.load(path.resolve(direction.getCapitalize() + ".png")));
            }
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        return returned;
    }

    public static Actor createActor(String systemName, Point start, Region region) throws GameException {
        String methodName = "create" + systemName.substring(0, 1).toUpperCase() + systemName.substring(1);
        try {
            return (Actor) ActorFactory.class.getDeclaredMethod(methodName, Point.class, Region.class).invoke(ActorFactory.class, start, region);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new GameException(e);
        }
    }

    @SuppressWarnings("unused")
    private static Pirate createPirate(Point start, Region region) {
        return new Pirate(start, region, resourceBundle.getString("pirate.name"));
    }
}
