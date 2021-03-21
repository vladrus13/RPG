package ru.vladrus13.core.bean;

import java.util.Objects;

/**
 * Point class. Class for designating a coordinate and its type
 */
public class Point {
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
     * Constructor for point with type
     *
     * @param x               x-axis coordinate
     * @param y               y-axis coordinate
     * @param coordinatesType type of point
     */
    public Point(long x, long y, CoordinatesType coordinatesType) {
        this.x = x;
        this.y = y;
        this.coordinatesType = coordinatesType;
    }

    /**
     * Constructor for real point
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     */
    public Point(long x, long y) {
        this.x = x;
        this.y = y;
        this.coordinatesType = CoordinatesType.REAL;
    }

    /**
     * Create copy of point
     *
     * @return copy
     */
    public Point copy() {
        return new Point(x, y, coordinatesType);
    }

    /**
     * Increase x-axis coordinate
     *
     * @param x how much should we increase the coordinate
     * @return new point
     */
    public Point incX(int x) {
        return new Point(this.x + x, this.y, this.coordinatesType);
    }

    /**
     * Increase y-axis coordinate
     *
     * @param y how much should we increase the coordinate
     * @return new point
     */
    public Point incY(int y) {
        return new Point(this.x, this.y + y, this.coordinatesType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && coordinatesType == point.coordinatesType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, coordinatesType);
    }
}
