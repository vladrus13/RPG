package ru.vladrus13.core.bean;

import java.util.Objects;

public class Point {
    public final long x;
    public final long y;
    public final CoordinatesType coordinatesType;

    public Point(long x, long y, CoordinatesType coordinatesType) {
        this.x = x;
        this.y = y;
        this.coordinatesType = coordinatesType;
    }

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
        this.coordinatesType = CoordinatesType.REAL;
    }

    public Point copy() {
        return new Point(x, y, coordinatesType);
    }

    public Point incX(int x) {
        return new Point(this.x + x, this.y, this.coordinatesType);
    }

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
