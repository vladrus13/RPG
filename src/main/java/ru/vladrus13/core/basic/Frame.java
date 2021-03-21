package ru.vladrus13.core.basic;


import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.utils.Ratio;

import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * The most important class.
 * Denotes some rectangle that can be drawn and can accept commands from keyboard and mouse.
 * This rectangle has its size and is set to the top left point.
 * These coordinates can be pixel-by-pixel (drawn on the screen) and relative to the parent.
 */
public abstract class Frame extends Drawn implements Focus {

    /**
     * Real start position on screen
     */
    protected Point start;
    /**
     * Real size on screen
     */
    protected Size size;
    /**
     * Ratio start position. Can be null if constructor contain real position
     */
    protected final Point ratioStart;
    /**
     * Ratio size. Can be null if constructor contain real size
     */
    protected final Size ratioSize;
    /**
     * Parent frame. Can be null if no parents are given
     */
    protected Frame parent;
    /**
     * Type of start position
     */
    protected final CoordinatesType startType;
    /**
     * Type of size
     */
    protected final CoordinatesType sizeType;
    /**
     * Focused class. The top deque is called on keyboard or mouse events
     */
    protected final Deque<Frame> focused = new LinkedList<>();
    /**
     * Logger class
     */
    protected final Logger logger = Logger.getLogger(Frame.class.getName());
    protected final String name;

    /**
     * Standard constructor for Frame
     *
     * @param name   system name of frame
     * @param start  start position for frame
     * @param size   frame size
     * @param parent parent frame
     */
    public Frame(String name, Point start, Size size, Frame parent) {
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
        this.name = name;
        startType = start.coordinatesType;
        sizeType = size.coordinatesType;
        this.parent = parent;
        recalculate();
    }

    /**
     * If there are some changes in the size of the windows, then this method is called
     */
    public void recalculate() {
        if (startType == CoordinatesType.RATIO && parent != null) {
            start = Ratio.getPoint(parent.start, parent.size, ratioStart);
        }
        if (sizeType == CoordinatesType.RATIO && parent != null) {
            size = Ratio.getSize(parent.size, ratioSize);
        }
    }

    /**
     * Recalculate all childes
     */
    public abstract void recalculateChildes();

    /**
     * If there are some changes in the size of the windows, then this method is called
     *
     * @param width  new width of frame
     * @param height new height of frame
     */
    public void recalculate(int width, int height) {
        size = new Size(width, height, CoordinatesType.REAL);
        recalculate();
    }

    /**
     * Set new parent for frame
     *
     * @param parent new parent
     */
    public void setParent(Frame parent) {
        this.parent = parent;
        recalculate();
    }

    /**
     * Getter for real position of frame
     *
     * @return real position of frame
     */
    public Point getStart() {
        return start;
    }

    /**
     * Getter for frame size
     *
     * @return frame size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Add new focused on top of deque
     *
     * @param focused new focused
     */
    public void addFocused(Frame focused) {
        this.focused.addFirst(focused);
    }

    /**
     * Remove focused from top of deque
     */
    public void removeFocused() {
        this.focused.removeFirst();
    }

    /**
     * Remove focused from top of deque with equality check
     *
     * @param frame which should be on top of deque
     * @throws GameException if the wrong frame is on top of the deque
     */
    public void removeFocused(Frame frame) throws GameException {
        if (this.focused.getFirst() != frame) {
            throw new GameException("Current focused frame not equal removed");
        }
        removeFocused();
    }
}
