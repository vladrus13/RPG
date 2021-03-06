package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.services.FontService;

import java.awt.*;
import java.util.Collections;

public class Text extends Frame {

    public enum TextAlign {
        CENTER, LEFT, RIGHT
    }

    private final String text;
    private final Font font;
    private final Color color;
    private final TextAlign textAlign;
    private Point textStart;

    public Text(Point start, Size size, Frame parent, String text, Font font, Color color, TextAlign textAlign) {
        super(start, size, Collections.emptyList(), parent);
        this.text = text;
        this.color = color;
        this.font = font;
        this.textAlign = textAlign;
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, (int) textStart.x, (int) textStart.y);
    }

    @Override
    public void recalculate() {
        // TODO make two or more strings on one text
        super.recalculate();
        int textHeight = font.getSize();
        int textWidth = FontService.fontWidth(text, font);
        int textHeightStart = (int) (size.y / 2) + textHeight / 2;
        int textWidthStart = -1;
        switch (textAlign) {
            case LEFT:
                textWidthStart = 0;
                break;
            case RIGHT:
                textWidthStart = (int) (size.x - textWidth);
                break;
            case CENTER:
                textWidthStart = (int) (size.y / 2) - textWidth / 2;
                break;
        }
        textStart = new Point(textWidthStart, textHeightStart, CoordinatesType.REAL);
    }
}
