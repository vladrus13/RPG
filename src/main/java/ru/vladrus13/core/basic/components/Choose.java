package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.KeyTaker;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Choose extends Frame implements KeyTaker {
    private final ArrayList<Button> buttons;
    private int current;

    public Choose(Point start, Size size, ArrayList<Button> buttons, Frame parent) {
        super(start, size, new ArrayList<>(buttons), parent);
        this.buttons = buttons;
        current = 0;
        if (!buttons.isEmpty()) buttons.get(0).setChoose(true);
        recalculate();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (Button button : buttons) {
            button.draw(graphics);
        }
    }

    @Override
    public int keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                buttons.get(current).setChoose(false);
                current = (current + 1) % buttons.size();
                buttons.get(current).setChoose(true);
                break;
            case KeyEvent.VK_DOWN:
                buttons.get(current).setChoose(false);
                current = (current + current - 1) % buttons.size();
                buttons.get(current).setChoose(true);
                break;
            case KeyEvent.VK_ENTER:
                return buttons.get(current).keyPressed(e);
        }
        return 0;
    }

    @Override
    public int mousePressed(MouseEvent e) {

        return 0;
    }

    public void addButton(Button button) {
        addFrames(button);
        if (buttons.isEmpty()) {
            button.setChoose(true);
        }
        buttons.add(button);
    }
}
