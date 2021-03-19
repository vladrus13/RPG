package ru.vladrus13.core.basic;

import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.util.Collection;

/**
 * Frame with updates
 */
public abstract class UpdatedFrame extends Frame implements Updated {

    /**
     * If isPause true, frame will not update
     */
    protected boolean isPause = false;

    /**
     * Standard frame constructor
     * @param start start position of frame
     * @param size frame size
     * @param frames childes frames. For any changes, updates or redrawing, they are called for the corresponding operation
     * @param parent parent frame
     */
    public UpdatedFrame(Point start, Size size, Collection<Frame> frames, Frame parent) {
        super(start, size, frames, parent);
    }

    @Override
    public void update(long delay) {
        if (!isPause) {
            nonCheckingUpdate(delay);
        }
    }

    /**
     * is this frame paused, that is, it is not-updatable
     * @return paused or not
     */
    public boolean isPause() {
        return isPause;
    }

    /**
     * Set updatable of frame
     * @param pause updatable or not
     */
    public void setPause(boolean pause) {
        isPause = pause;
    }

    /**
     * Update this object with checking isPause
     * @param delay millisecond from last update for this object
     */
    protected abstract void nonCheckingUpdate(long delay);
}
