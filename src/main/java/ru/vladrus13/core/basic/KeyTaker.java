package ru.vladrus13.core.basic;

import ru.vladrus13.game.basic.event.returned.ReturnEvent;

import java.awt.event.KeyEvent;

public interface KeyTaker {
    ReturnEvent keyPressed(KeyEvent e);
}
