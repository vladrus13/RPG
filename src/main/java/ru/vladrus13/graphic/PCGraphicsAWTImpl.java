package ru.vladrus13.graphic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PCGraphicsAWTImpl implements Graphics {
    public final java.awt.Graphics graphics;

    public PCGraphicsAWTImpl(java.awt.Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void setColor(Color color) {
        graphics.setColor(color);
    }

    @Override
    public void setFont(Font font) {
        graphics.setFont(font);
    }

    @Override
    public void drawString(String text, long x, long y) {
        graphics.drawString(text, (int) x, (int) y);
    }

    @Override
    public void fillRect(long x, long y, long x1, long y1) {
        graphics.fillRect((int) x, (int) y, (int) x1, (int) y1);
    }

    @Override
    public void drawImage(BufferedImage image, long x, long y, long x1, long y1) {
        graphics.drawImage(image, (int) x, (int) y, (int) x1, (int) y1, null);
    }
}
