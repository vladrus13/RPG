package ru.vladrus13.core.resources;

import ru.vladrus13.core.exception.GameException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private final static Map<String, BufferedImage> images = new HashMap<>();
    private final static Path resourcesPath = Path.of("src").resolve("main").resolve("resources").resolve("graphic");

    /**
     * Return image from resources
     *
     * @param name {@link Path} FROM resources
     * @return {@link BufferedImage} with image
     * @throws GameException If we can't get image
     */
    public static BufferedImage load(Path name) throws GameException {
        if (!images.containsKey(name.toString())) {
            try {
                images.put(name.toString(), ImageIO.read(Files.newInputStream(resourcesPath.resolve(name))));
            } catch (IOException e) {
                throw new GameException("Can't load image: " + name, e);
            }
        }
        return images.get(name.toString());
    }
}
