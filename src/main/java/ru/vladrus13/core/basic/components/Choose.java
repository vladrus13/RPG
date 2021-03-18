package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.KeyTaker;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Class with one or more buttons embodying choice
 *
 * @author vladrus13
 */
public class Choose extends Frame implements KeyTaker {
    /**
     * List of buttons
     */
    private final ArrayList<Button> buttons;
    /**
     * Number of current button. If button doesn't exist is zero
     */
    private int current;

    /**
     * Standard constructor for class
     *
     * @param start   start position for choose
     * @param size    size of choose class
     * @param buttons list of buttons
     * @param parent  parent frame
     */
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
    public ReturnEvent keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                buttons.get(current).setChoose(false);
                current = (current + 1) % buttons.size();
                buttons.get(current).setChoose(true);
                break;
            case KeyEvent.VK_DOWN:
                buttons.get(current).setChoose(false);
                current = (current + buttons.size() - 1) % buttons.size();
                buttons.get(current).setChoose(true);
                break;
            case KeyEvent.VK_ENTER:
                return buttons.get(current).keyPressed(e);
        }
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    /**
     * Add button to end of button list
     *
     * @param button button
     * @see Button
     */
    public void addButton(Button button) {
        addFrames(button);
        if (buttons.isEmpty()) {
            button.setChoose(true);
        }
        buttons.add(button);
    }
}
