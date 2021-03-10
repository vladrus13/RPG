package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.graphic.Graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Collections;

public class Background extends Frame {

    private final BufferedImage image;
    private final Color color;

    public Background(Point start, Size size, BufferedImage image, Frame parent) {
        super(start, size, Collections.emptyList(), parent);
        this.image = image;
        color = null;
        recalculate();
    }

    public Background(Point start, Size size, Color color, Frame parent) {
        super(start, size, Collections.emptyList(), parent);
        this.color = color;
        image = null;
        recalculate();
    }

    public Background(Color color, Frame parent) {
        super(parent.getStart(), parent.getSize(), Collections.emptyList(), parent);
        this.color = color;
        this.image = null;
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(start.x, start.y, size.x, size.y);
        }
        if (image != null) {
            graphics.drawImage(image, start.x, start.y, size.x, size.y);
        }
    }

    @Override
    public int keyPressed(KeyEvent e) {
        return 0;
    }

    @Override
    public int mousePressed(MouseEvent e) {

        return 0;
    }
}
