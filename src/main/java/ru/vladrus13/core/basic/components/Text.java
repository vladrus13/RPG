package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.services.FontService;
import ru.vladrus13.core.utils.Writer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collections;

public class Text extends Frame {

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    public enum TextAlign {
        CENTER, LEFT, RIGHT
    }

    private final String text;
    private Font font;
    private final Color color;
    private final TextAlign textAlign;
    private Point textStart;
    private final Size fontSize;

    public Text(Point start, Size size, Frame parent, String text, String nameFont, Size fontSize, Color color, TextAlign textAlign) throws GameException {
        super(start, size, Collections.emptyList(), parent);
        this.text = text;
        this.color = color;
        this.font = FontService.getFont(nameFont, (int) fontSize.x);
        this.textAlign = textAlign;
        this.fontSize = fontSize;
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect((int) start.x, (int) start.y, (int) size.x, (int) size.y);
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, (int) textStart.x, (int) textStart.y);
    }

    @Override
    public void recalculate() {
        // TODO make two or more strings on one text
        super.recalculate();
        if (fontSize.coordinatesType == CoordinatesType.RATIO) {
            float newSizeFont = (fontSize.x * size.y) / 1000f;
            font = font.deriveFont(newSizeFont);
        }
        int textHeight = font.getSize();
        int textWidth = FontService.fontWidth(text, font);
        int textHeightStart = (int) (size.y / 2) + textHeight / 2;
        if (textHeight > size.x) {
            logger.warning("Text size greater than size of panel: x");
        }
        if (textWidth > size.y) {
            logger.warning("Text size greater than size of panel: y");
        }
        int textWidthStart = -1;
        switch (textAlign) {
            case LEFT:
                textWidthStart = 0;
                break;
            case RIGHT:
                textWidthStart = (int) (size.x - textWidth);
                break;
            case CENTER:
                textWidthStart = (int) (size.x / 2) - textWidth / 2;
                break;
        }
        textStart = new Point(start.x + textWidthStart, start.y + textHeightStart, CoordinatesType.REAL);
    }
}
