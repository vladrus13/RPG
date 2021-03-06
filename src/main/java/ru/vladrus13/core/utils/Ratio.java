package ru.vladrus13.core.utils;

import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.property.MainProperty;

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
                startParent.x + (start.x * parentSize.x / MainProperty.getLong("window.ratio")),
                startParent.y + (start.y * parentSize.y / MainProperty.getLong("window.ratio")),
                CoordinatesType.REAL);
    }

    public static Size getSize(Size parentSize, Size size) {
        if (size.coordinatesType == CoordinatesType.REAL) {
            return size.copy();
        }
        return new Size(
                size.x * parentSize.x / MainProperty.getLong("window.ratio"),
                size.y * parentSize.y / MainProperty.getLong("window.ratio"),
                CoordinatesType.REAL);
    }
}
