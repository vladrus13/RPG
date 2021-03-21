package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Implementation of button class. Return Event if button activated. Use <b>Builder Pattern</b>
 *
 * @author vladrus13
 * @see ReturnEvent
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern">Builder pattern</a>
 */
public class ClassicButton extends Button {

    /**
     * Event if button activate
     */
    private ReturnEvent event = new ReturnInt(ReturnInt.PRESSED);

    /**
     * Classic frame constructor for button
     *
     * @param name   system name of frame
     * @param start  start position for button
     * @param size   size of button
     * @param parent parent frame
     */
    public ClassicButton(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            return event;
        }
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    /**
     * Set event on activate button
     *
     * @param event event
     * @return this button
     */
    public ClassicButton setEvent(ReturnEvent event) {
        this.event = event;
        return this;
    }
}
