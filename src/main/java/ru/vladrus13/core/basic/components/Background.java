package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;
import ru.vladrus13.graphic.Graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Collections;

/**
 * Background class. You can fill it from image or color
 *
 * @author vladrus13
 */
public class Background extends Frame {

    /**
     * Is null if a color is selected
     */
    private final BufferedImage image;
    /**
     * Is null if a image is selected
     */
    private final Color color;

    /**
     * Constructor for Background with image
     *
     * @param start  start position
     * @param size   size
     * @param image  image
     * @param parent parent for this frame
     */
    public Background(Point start, Size size, BufferedImage image, Frame parent) {
        super(start, size, Collections.emptyList(), parent);
        this.image = image;
        color = null;
        recalculate();
    }

    /**
     * Constructor for Background with color
     *
     * @param start  start position
     * @param size   size
     * @param color  color
     * @param parent parent for this frame
     */
    public Background(Point start, Size size, Color color, Frame parent) {
        super(start, size, Collections.emptyList(), parent);
        this.color = color;
        image = null;
        recalculate();
    }

    /**
     * Parent fill background
     *
     * @param color  color
     * @param parent parent
     */
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
    public ReturnEvent keyPressed(KeyEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }
}
