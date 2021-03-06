package ru.vladrus13.core.bean;

public class Size {

    public final long x;
    public final long y;
    public final CoordinatesType coordinatesType;

    public Size(long x, long y, CoordinatesType coordinatesType) {
        this.x = x;
        this.y = y;
        this.coordinatesType = coordinatesType;
    }

    public Size copy() {
        return new Size(x, y, coordinatesType);
    }
}