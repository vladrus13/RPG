package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.resources.ImageLoader;
import ru.vladrus13.game.basic.returned.ReturnEvent;
import ru.vladrus13.game.basic.returned.ReturnInt;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Collections;

public class Image extends Frame {

    private final BufferedImage image;

    public Image(Point start, Size size, Path path, Frame parent) throws GameException {
        super(start, size, Collections.emptyList(), parent);
        image = ImageLoader.load(path);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(image, start.x, start.y, size.x, size.y);
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public int mousePressed(MouseEvent e) {
        return ReturnInt.NOTHING;
    }
}
