package com.sibb.visual;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Panel {
    /**
     * Method getCurrentHeight.
     *
     * @return int
     */
    public int getCurrentHeight() {
        return currentHeight;
    }

    /**
     * Method getInitialHeight.
     *
     * @return int
     */
    public int getInitialHeight() {
        return initialHeight;
    }

    /**
     * Method getMaxHeight.
     *
     * @return int
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * Method setCurrentHeight.
     *
     * @param currentHeight int
     */
    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
    }

    /**
     * Method setInitialHeight.
     *
     * @param initialHeight int
     */
    public void setInitialHeight(int initialHeight) {
        this.initialHeight = initialHeight;
    }

    /**
     * Method setMaxHeight.
     *
     * @param maxHeight int
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     * Constructor for Panel.
     *
     * @param maxHeight     int
     * @param initialHeight int
     */
    public Panel(int maxHeight, int initialHeight) {
        this.maxHeight = maxHeight;
        this.currentHeight = initialHeight;
        this.initialHeight = initialHeight;
    }

    int maxHeight, currentHeight = 0, initialHeight = 0;
}
