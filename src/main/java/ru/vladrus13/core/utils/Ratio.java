package ru.vladrus13.core.utils;

import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

/**
 * Ratio class. Рelps to convert coordinates and dimensions
 */
public class Ratio {
    /**
     * Get real start coordinates of frame by parent and start coordinates relatively parent
     * @param startParent real start point of parent
     * @param parentSize real size of parent
     * @param start relatively position of parent
     * @return real start coordinates
     */
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

    /**
     * Get real size by parent size and relatively parent size
     * @param parentSize real parent size
     * @param size relatively size
     * @return real size of child
     */
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
