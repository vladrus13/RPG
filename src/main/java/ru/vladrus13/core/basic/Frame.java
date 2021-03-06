package ru.vladrus13.core.basic;


import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.utils.Ratio;

import java.util.Collection;

public abstract class Frame extends Drawn {

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
    protected final Frame parent;
    protected final CoordinatesType startType;
    protected final CoordinatesType sizeType;

    public Frame(Point start, Size size, Collection<Frame> frames) {
        startType = start.coordinatesType;
        sizeType = size.coordinatesType;
        this.start = start;
        this.frames = frames;
        this.size = size;
        this.parent = null;
        ratioStart = null;
        ratioSize = null;
    }

    public Frame(Point start, Size size, Collection<Frame> frames, Frame parent) {
        if (start.coordinatesType == CoordinatesType.REAL) {
            this.start = start;
        } else {
            this.ratioStart = start;
        }
        if (size.coordinatesType == CoordinatesType.REAL) {
            this.size = size;
        } else {
            this.ratioSize = size;
        }
        startType = start.coordinatesType;
        sizeType = size.coordinatesType;
        this.ratioStart = start;
        this.ratioSize = size;
        this.frames = frames;
        this.parent = parent;
        recalculate();
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
}
