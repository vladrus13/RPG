package ru.vladrus13.core.utils;

import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

public class Ratio {
    public static Point getPoint(Point startParent, Size parentSize, Point start) {
        if (start.coordinatesType == CoordinatesType.REAL) {
            return new Point(
                    startParent.x + start.x,
                    startParent.y + start.y,
                    CoordinatesType.REAL
            );
        }
        return new Point(
                startParent.x + (start.x * parentSize.x / 1000),
                startParent.y + (start.y * parentSize.y / 1000),
                CoordinatesType.REAL);
    }

    public static Size getSize(Size parentSize, Size size) {
        if (size.coordinatesType == CoordinatesType.REAL) {
            return size.copy();
        }
        return new Size(
                size.x * parentSize.x / 1000,
                size.y * parentSize.y / 1000,
                CoordinatesType.REAL);
    }
}
