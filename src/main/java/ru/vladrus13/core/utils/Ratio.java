package ru.vladrus13.core.utils;

import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.property.MainProperty;

public class Ratio {
    public static Point getPoint(Point startParent, Size parentSize, Point startRatio) {
        return new Point(
                startParent.x + (startRatio.x * parentSize.x / MainProperty.getLong("window.ratio")),
                startParent.y + (startRatio.y * parentSize.y / MainProperty.getLong("window.ratio")));
    }

    public static Size getSize(Size parentSize, Size ratioSize) {
        return new Size(
                ratioSize.x * parentSize.x / MainProperty.getLong("window.ratio"),
                ratioSize.y * parentSize.y / MainProperty.getLong("window.ratio"));
    }
}
