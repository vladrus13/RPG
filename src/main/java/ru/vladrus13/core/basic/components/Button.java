package ru.vladrus13.core.basic.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.graphic.Graphics;

import java.util.ArrayList;

/**
 * Button class for graphics. Can be touched and activate. Use <b>Builder pattern</b>.
 *
 * @author vladrus13
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern">Builder pattern</a>
 */
public abstract class Button extends Frame {

    /**
     * Is button selected
     */
    private boolean choose;
    /**
     * Button background if button not selected
     */
    private Background background;
    /**
     * Button background if button selected
     */
    private Background backgroundChoose;
    /**
     * Text on button
     */
    private Text text;

    /**
     * Constructor for button
     *
     * @param start  start position for button
     * @param size   button size
     * @param parent parent
     */
    public Button(Point start, Size size, Frame parent) {
        super(start, size, new ArrayList<>(), parent);
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        if (choose) {
            backgroundChoose.draw(graphics);
        } else {
            background.draw(graphics);
        }
        text.draw(graphics);
    }

    /**
     * Set button selected or not
     *
     * @param choose selected or not
     */
    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    /**
     * Set background drawn when button is not selected
     *
     * @param background background drawn when button is not selected
     */
    public void setBackground(Background background) {
        frames.remove(this.background);
        this.background = background;
        frames.add(background);
    }

    /**
     * Set background drawn when button is selected
     *
     * @param backgroundChoose background drawn when button is selected
     */
    public void setBackgroundChoose(Background backgroundChoose) {
        frames.remove(this.backgroundChoose);
        this.backgroundChoose = backgroundChoose;
        frames.add(backgroundChoose);
    }

    /**
     * Set text
     *
     * @param text text on button
     */
    public void setText(Text text) {
        frames.remove(this.text);
        this.text = text;
        frames.add(text);
    }
}
