package ru.vladrus13.core.basic;

import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

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
     *
     * @param name   system name of frame
     * @param start  start position of frame
     * @param size   frame size
     * @param parent parent frame
     */
    public UpdatedFrame(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
    }

    @Override
    public void update(long delay) {
        if (!isPause) {
            nonCheckingUpdate(delay);
        }
    }

    /**
     * is this frame paused, that is, it is not-updatable
     *
     * @return paused or not
     */
    public boolean isPause() {
        return isPause;
    }

    /**
     * Set updatable of frame
     *
     * @param pause updatable or not
     */
    public void setPause(boolean pause) {
        isPause = pause;
    }

    /**
     * Update this object with checking isPause
     *
     * @param delay millisecond from last update for this object
     */
    protected abstract void nonCheckingUpdate(long delay);
}
