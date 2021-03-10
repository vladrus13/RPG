package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.game.basic.ReturnKeys;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ClassicButton extends Button {
    public ClassicButton(Point start, Size size, Frame parent) {
        super(start, size, parent);
    }

    @Override
    public int keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            return ReturnKeys.PRESSED;
        }
        return ReturnKeys.NOTHING;
    }

    @Override
    public int mousePressed(MouseEvent e) {
        return ReturnKeys.NOTHING;
    }
}
