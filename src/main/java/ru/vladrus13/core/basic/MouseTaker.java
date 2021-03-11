package ru.vladrus13.core.basic;

import ru.vladrus13.game.basic.returned.ReturnEvent;

import java.awt.event.MouseEvent;

public interface MouseTaker {
    ReturnEvent mousePressed(MouseEvent e);
}
