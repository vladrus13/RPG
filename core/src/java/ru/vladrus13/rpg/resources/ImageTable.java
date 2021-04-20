package ru.vladrus13.rpg.resources;

import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.resources.ImageLoader;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageTable {
    public final Path path;
    public final Size size;
    public final BufferedImage[][] images;

    public ImageTable(Path path, Size size) throws GameException {
        this.path = path;
        this.size = size;
        BufferedImage bufferedImage = ImageLoader.load(path);
        if (bufferedImage.getHeight() % size.y != 0) {
            throw new GameException("Can't divide image height by size of one picture");
        }
        if (bufferedImage.getWidth() % size.x != 0) {
            throw new GameException("Can't divide image width by size of one picture");
        }
        int countHeight = bufferedImage.getHeight() / (int) size.y;
        int countWidth = bufferedImage.getWidth() / (int) size.x;
        images = new BufferedImage[countHeight][countWidth];
        for (int i = 0; i < countHeight; i++) {
            for (int j = 0; j < countWidth; j++) {
                images[i][j] = bufferedImage.getSubimage(j * (int) size.x,
                        i * (int) size.y,
                        (int) size.x,
                        (int) size.y);
            }
        }
    }

    public BufferedImage get(int width, int height) {
        return images[height][width];
    }

    public BufferedImage get(int count) {
        return images[count / images[0].length][count % images[0].length];
    }

    public ArrayList<BufferedImage> getAll() {
        ArrayList<BufferedImage> returned = new ArrayList<>(images[0].length * images.length);
        for (BufferedImage[] it : images) {
            returned.addAll(Arrays.asList(it));
        }
        return returned;
    }
}
