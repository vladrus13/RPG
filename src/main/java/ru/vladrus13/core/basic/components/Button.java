package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Button extends Frame {

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

    @Override
    public void recalculate() {
        super.recalculate();
    }

    public void setBackground(Background background) {
        this.background = background;
        // TODO REMOVE
        frames.add(background);
    }

    public void setBackgroundChoose(Background backgroundChoose) {
        this.backgroundChoose = backgroundChoose;
        // TODO REMOVE
        frames.add(backgroundChoose);
    }

    public void setText(Text text) {
        this.text = text;
        // TODO REMOVE
        frames.add(text);
    }
}
