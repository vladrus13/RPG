package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.graphic.Graphics;

import java.util.ArrayList;

/**
 * Button class for graphics. Can be touched and activate
 * @author vladrus13
 */
public abstract class Button extends Frame {

    /**
     * is button selected
     */
    private boolean choose;
    private Background background;
    private Background backgroundChoose;
    private Text text;

    public Button(Point start, Size size, Frame parent) {
        super(start, size, new ArrayList<>(), parent);
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        if (choose) {
            backgroundChoose.draw(graphics);
        } else {
            background.draw(graphics);
        }
        text.draw(graphics);
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public void setBackground(Background background) {
        frames.remove(this.background);
        this.background = background;
        frames.add(background);
    }

    public void setBackgroundChoose(Background backgroundChoose) {
        frames.remove(this.backgroundChoose);
        this.backgroundChoose = backgroundChoose;
        frames.add(backgroundChoose);
    }

    public void setText(Text text) {
        frames.remove(this.text);
        this.text = text;
        frames.add(text);
    }
}
