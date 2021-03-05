package ru.vladrus13.core.basic;


import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.utils.Ratio;

import java.awt.*;
import java.util.Collection;

public abstract class Frame extends Drawn {

    /**
     * Real position on screen
     */
    private Point start;
    /**
     * Real size on screen
     */
    private Size size;
    private Point ratioStart;
    private Size ratioSize;
    private final Collection<Drawn> drawns;
    private final Frame parent;

    public Frame(Point start, Size size, Collection<Drawn> drawns) {
        this.start = start;
        this.drawns = drawns;
        this.size = size;
        this.parent = null;
        ratioStart = null;
        ratioSize = null;
    }

    public Frame(Point ratioStart, Size ratioSize, Collection<Drawn> drawns, Frame parent) {
        this.ratioStart = ratioStart;
        this.ratioSize = ratioSize;
        this.drawns = drawns;
        this.parent = parent;
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        for (Drawn frame : drawns) {
            frame.draw(graphics);
        }
    }

    public void recalculate() {
        start = Ratio.getPoint(parent.start, parent.size, ratioStart);
        size = Ratio.getSize(parent.size, ratioSize);
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

    public void addDrawn(Drawn drawn) {
        drawns.add(drawn);
    }
}
