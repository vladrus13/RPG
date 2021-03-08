package ru.vladrus13.core.basic;


import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.utils.Ratio;

import java.util.Collection;
import java.util.logging.Logger;

public abstract class Frame extends Drawn implements Focus {

    /**
     * Real position on screen
     */
    protected Point start;
    /**
     * Real size on screen
     */
    protected Size size;
    protected Point ratioStart;
    protected Size ratioSize;
    protected final Collection<Frame> frames;
    protected Frame parent;
    protected final CoordinatesType startType;
    protected final CoordinatesType sizeType;
    protected Frame focused = null;
    protected final Logger logger = Logger.getLogger(Frame.class.getName());

    public Frame(Point start, Size size, Collection<Frame> frames, Frame parent) {
        if (start.coordinatesType == CoordinatesType.REAL) {
            this.start = start;
            this.ratioStart = null;
        } else {
            this.ratioStart = start;
            this.start = null;
        }
        if (size.coordinatesType == CoordinatesType.REAL) {
            this.size = size;
            this.ratioSize = null;
        } else {
            this.ratioSize = size;
            this.size = null;
        }
        startType = start.coordinatesType;
        sizeType = size.coordinatesType;
        this.frames = frames;
        this.parent = parent;
    }

    public void recalculate() {
        if (startType == CoordinatesType.RATIO && parent != null) {
            start = Ratio.getPoint(parent.start, parent.size, ratioStart);
        }
        if (sizeType == CoordinatesType.RATIO && parent != null) {
            size = Ratio.getSize(parent.size, ratioSize);
        }
        for (Frame frame : frames) {
            frame.recalculate();
        }
    }

    public void recalculate(int width, int height) {
        size = new Size(width, height, CoordinatesType.REAL);
        recalculate();
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setRatioStart(Point ratioStart) {
        this.ratioStart = ratioStart;
    }

    public void setRatioSize(Size ratioSize) {
        this.ratioSize = ratioSize;
    }

    public void addFrames(Frame drawn) {
        frames.add(drawn);
    }

    public void setParent(Frame parent) {
        this.parent = parent;
        recalculate();
    }

    public Point getStart() {
        return start;
    }

    public Size getSize() {
        return size;
    }
}
