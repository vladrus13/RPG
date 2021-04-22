package ru.vladrus13.rpg.dialog;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Dialog extends Frame {
    public Dialog(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
