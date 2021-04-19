package ru.vladrus13.rpg.basic.animation;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.basic.event.animation.AnimationEventFrameEnd;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AnimationFrame extends UpdatedFrame {

    private final Filler filler;
    private final int coolDown;
    private int current;

    /**
     * Standard frame constructor
     *
     * @param name   system name of frame
     * @param start  start position of frame
     * @param size   frame size
     * @param parent parent frame
     */
    public AnimationFrame(String name, Point start, Size size, Animation parent, Filler filler, int coolDown) {
        super(name, start, size, parent);
        this.filler = filler;
        this.coolDown = coolDown;
        this.current = 0;
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        current += delay;
        if (current > coolDown) {
            callEvent(new AnimationEventFrameEnd());
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (filler.getBufferedImage() != null) {
            graphics.drawImage(filler.getBufferedImage(), start.x, start.y, size.x, size.y);
        }
        if (filler.getColor() != null) {
            graphics.setColor(filler.getColor());
            graphics.fillRect(start.x, start.y, size.x, size.y);
        }
        throw new IllegalStateException("Can't draw anything");
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }
}
