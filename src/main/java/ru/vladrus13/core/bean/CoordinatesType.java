package ru.vladrus13.core.bean;

/**
 * Coordinates type.
 * This class is needed in order to understand how the size of children should change when the parent changes.
 */
public enum CoordinatesType {
    /**
     * Ratio means that the class belonging to the parent will change according to the ratio given in dimensions
     */
    RATIO,
    /**
     * Real means that the child will not change relative to the parent and will not change their children
     */
    REAL
}
