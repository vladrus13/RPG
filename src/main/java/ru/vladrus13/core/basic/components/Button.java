package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.KeyTaker;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;

public class Button extends Frame {

    private boolean choose;
    private final Background background;
    private final Background backgroundChoose;
    private final Text text;

    public Button(Point start, Size size, Frame parent, Background background, Background backgroundChoose, Text text) {
        super(start, size, Arrays.asList(background, text, backgroundChoose), parent);
        this.background = background;
        this.backgroundChoose = backgroundChoose;
        this.text = text;
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

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    @Override
    public void recalculate() {
        super.recalculate();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
