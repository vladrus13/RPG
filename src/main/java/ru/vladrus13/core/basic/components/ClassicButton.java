package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.game.basic.returned.ReturnEvent;
import ru.vladrus13.game.basic.returned.ReturnInt;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ClassicButton extends Button {

    private ReturnEvent event = new ReturnInt(ReturnInt.PRESSED);

    public ClassicButton(Point start, Size size, Frame parent) {
        super(start, size, parent);
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

    public ClassicButton setEvent(ReturnEvent event) {
        this.event = event;
        return this;
    }
}
