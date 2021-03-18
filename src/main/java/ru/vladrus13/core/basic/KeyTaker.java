package ru.vladrus13.core.basic;

import ru.vladrus13.game.basic.event.returned.ReturnEvent;

import java.awt.event.KeyEvent;

/**
 * A class that mean this object can accept requests from the keyboard
 */
public interface KeyTaker {
    /**
     * Get request from keyboard
     *
     * @param e request from keyboard
     * @return response from request
     */
    ReturnEvent keyPressed(KeyEvent e);
}
