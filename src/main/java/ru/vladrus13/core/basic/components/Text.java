package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Drawn;
import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.awt.*;
import java.util.Collections;

public class Text extends Frame {

    private final String text;
    private final Font font;
    private final Color color;

    public Text(Point start, Size size, Frame parent, String text, Font font, Color color) {
        super(start, size, Collections.emptyList(), parent);
        this.text = text;
        this.color = color;
        this.font = font;
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, (int) start.x, (int) start.y);
    }
}
