package ru.vladrus13.core.basic;

import ru.vladrus13.graphic.Graphics;

/**
 * Abstract class for all objects that will be drawn
 */
public abstract class Drawn {

    /**
     * Need to draw this class. Needed to sometimes hide class
     */
    protected boolean isDrawing = true;

    /**
     * Draw class without checking isDrawing
     *
     * @param graphics graphics
     */
    protected abstract void nonCheckingDraw(Graphics graphics);

    /**
     * Draw class with checking isDrawing
     *
     * @param graphics graphics
     */
    public void draw(Graphics graphics) {
        if (isDrawing) {
            nonCheckingDraw(graphics);
        }
    }

    /**
     * Is drawing this object
     *
     * @return is drawing this class
     */
    public boolean isDrawing() {
        return isDrawing;
    }

    /**
     * Set is drawing this object
     *
     * @param drawing is drawing this class
     */
    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }
}
