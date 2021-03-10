package ru.vladrus13.core.basic;

import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.util.Collection;

public abstract class UpdatedFrame extends Frame implements Updated {

    protected boolean isPause = false;

    public UpdatedFrame(Point start, Size size, Collection<Frame> frames, Frame parent) {
        super(start, size, frames, parent);
    }

    @Override
    public void update(long delay) {
        if (!isPause) {
            nonCheckingPause(delay);
        }
    }

    @Override
    public void setPause(boolean pause) {
        isPause = pause;
    }

    protected abstract void nonCheckingPause(long delay);
}
