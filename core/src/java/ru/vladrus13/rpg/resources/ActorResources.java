package ru.vladrus13.rpg.resources;

import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.jgraphic.resources.ImageLoader;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.basic.direction.Direction;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ActorResources {

    private final static Logger logger = Logger.getLogger(ActorResources.class.getName());

    private final static Path actorsPath = Path.of("world").resolve("actors");
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("internationalization.actors", MainProperty.getLocale());

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

    public static BufferedImage loadActorFace(String name) {
        Path path = actorsPath.resolve(name);
        try {
            return ImageLoader.load(path.resolve("face.png"));
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        return null;
    }

    public static String getResourcesBundle(String get) {
        return resourceBundle.getString(get);
    }
}
