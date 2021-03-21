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
     * @param name   system name of frame
     * @param start  start position for choose
     * @param size   size of choose class
     * @param parent parent frame
     */
    public Choose(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
        this.buttons = new ArrayList<>();
        current = 0;
        recalculateChildes();
    }

    @Override
    public void recalculateChildes() {
        for (Button button : buttons) {
            button.recalculate();
        }
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
        if (buttons.isEmpty()) {
            button.setChoose(true);
        }
        buttons.add(button);
    }
}
