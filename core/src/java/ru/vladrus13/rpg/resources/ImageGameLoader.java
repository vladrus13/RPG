package ru.vladrus13.rpg.resources;

import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.utils.Writer;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ImageGameLoader {

    public static final Logger logger = Logger.getLogger(ImageGameLoader.class.getName());
    public static final Map<Path, ImageTable> images = new HashMap<>();

    public static BufferedImage load(Path path, int width, int height) {
        return images.get(path).get(width, height);
    }

    public static void upload(Path path, Size size) {
        if (!images.containsKey(path)) {
            try {
                images.put(path, new ImageTable(path, size));
            } catch (GameException e) {
                Writer.printStackTrace(logger, e);
            }
        }
    }
}
