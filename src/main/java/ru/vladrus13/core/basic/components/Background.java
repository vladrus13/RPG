package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;

public class Background extends Frame {

    private final BufferedImage image;
    private final Color color;

    public Background(Point start, Size size, Frame parent, BufferedImage image) {
        super(start, size, Collections.emptyList(), parent);
        this.image = image;
        color = null;
        recalculate();
    }

    public Background(Point start, Size size, Frame parent, Color color) {
        super(start, size, Collections.emptyList(), parent);
        this.color = color;
        image = null;
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect((int) start.x, (int) start.y, (int) size.x, (int) size.y);
        }
        if (image != null) {
            graphics.drawImage(image, (int) start.x, (int) start.y, (int) size.x, (int) size.y, null);
        }
    }
}
