package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.services.FontService;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;
import ru.vladrus13.graphic.Graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Text class. Using for drawing text on screen
 */
public class Text extends Frame {

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    /**
     * Text align. Helper class for {@link Text}
     */
    public enum TextAlign {
        /**
         * Center align enum
         */
        CENTER,
        /**
         * Left align enum
         */
        LEFT,
        /**
         * Right align enum
         */
        RIGHT
    }

    /**
     * Text
     */
    private final String text;
    /**
     * Font for text
     *
     * @see Font
     */
    private Font font;
    /**
     * Color for text
     *
     * @see Color
     */
    private final Color color;
    /**
     * Text align
     *
     * @see TextAlign
     */
    private final TextAlign textAlign;
    /**
     * Start position for text (lower left corner)
     */
    private Point textStart;
    /**
     * Font size. Only need x-axis and type
     */
    private final Size fontSize;

    /**
     * Classic frame constructor for Text class. The text is contained in a rectangle given by size and start
     *
     * @param name      system name of frame
     * @param start     start position of text
     * @param size      size position of text
     * @param text      text
     * @param nameFont  name of font. An attempt will be made to find it using the FontService
     * @param fontSize  size of font. Only need x-axis and type
     * @param color     color of text.
     * @param textAlign text align
     * @param parent    parent frame
     * @throws GameException if we can't load a font
     * @see Color
     * @see Font
     * @see TextAlign
     */
    public Text(String name, Point start, Size size, String text, String nameFont, Size fontSize, Color color, TextAlign textAlign, Frame parent) throws GameException {
        super(name, start, size, parent);
        this.text = text;
        this.color = color;
        this.font = FontService.getFont(nameFont,
                (int) (fontSize.coordinatesType == CoordinatesType.RATIO ? ((fontSize.x * size.y) / 1000f) : fontSize.x));
        this.textAlign = textAlign;
        this.fontSize = fontSize;
        recalculateChildes();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, textStart.x, textStart.y);
    }

    @Override
    public void recalculateChildes() {
        // TODO make two or more strings on one text
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
