package com.sibb.world;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public abstract class Position {
    private int x, y;

    /**
     * Method getX.
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Method setX.
     *
     * @param x int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Method getY.
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Method setY.
     *
     * @param y int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Method translate.
     *
     * @param x int
     * @param y int
     */
    public void translate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Method setPos.
     *
     * @param x int
     * @param y int
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for Position.
     *
     * @param x int
     * @param y int
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
    }

}
