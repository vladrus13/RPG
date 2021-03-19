package ru.vladrus13.core.bean;

/**
 * Size class. Class for designating a szie and its type
 */
public class Size {
    /**
     * X-axis coordinate
     */
    public final long x;
    /**
     * Y-axis coordinate
     */
    public final long y;
    /**
     * The type of the coordinate relative to its parent
     */
    public final CoordinatesType coordinatesType;

    /**
     * Constructor for size with type
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @param coordinatesType type of point
     */
    public Size(long x, long y, CoordinatesType coordinatesType) {
        this.x = x;
        this.y = y;
        this.coordinatesType = coordinatesType;
    }

    /**
     * Create copy of size
     * @return copy
     */
    public Size copy() {
        return new Size(x, y, coordinatesType);
    }
}