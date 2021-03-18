package ru.vladrus13.core.basic;

/**
 * A class that mean this object can be updated over time
 */
public interface Updated {

    /**
     * Update this object
     *
     * @param delay millisecond from last update for this object
     */
    void update(long delay);

}
