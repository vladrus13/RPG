package ru.vladrus13.game.world.actors;

import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.resources.ImageLoader;
import ru.vladrus13.core.utils.Writer;
import ru.vladrus13.game.basic.direction.Direction;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ActorFactory {

    private final static Logger logger = Logger.getLogger(ActorFactory.class.getName());

    private final static Path actorsPath = Path.of("world").resolve("actors");

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

}
