package ru.vladrus13.core.basic;

import java.awt.*;

public abstract class Drawn {

    protected boolean isPause = false;
    protected boolean isDrawing = true;

    protected abstract void nonCheckingDraw(Graphics graphics);

    public void draw(Graphics graphics) {
        if (isDrawing) {
            nonCheckingDraw(graphics);
        }
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isDrawing() {
        return isDrawing;
    }

    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }
}
