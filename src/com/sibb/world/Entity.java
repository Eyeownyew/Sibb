package com.sibb.world;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public abstract class Entity extends Position {

    /**
     * Constructor for Entity.
     *
     * @param x      int
     * @param y      int
     * @param width  int
     * @param height int
     */
    public Entity(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    private int width, height;

    public Entity() {
    }

    /**
     * Method getWidth.
     *
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method setWidth.
     *
     * @param width int
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Method getHeight.
     *
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * Method setHeight.
     *
     * @param height int
     */
    public void setHeight(int height) {
        this.height = height;
        ;
    }

}
