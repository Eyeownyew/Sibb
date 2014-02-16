package com.sibb.visual;

import com.sibb.util.ImageLoader;

import java.awt.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public abstract class Interface {

    /**
     * Method getBounds.
     *
     * @return Rectangle
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Method getExitBounds.
     *
     * @return Rectangle
     */
    public Rectangle getExitBounds() {
        return null;
    }

    /**
     * Method isExitable.
     *
     * @return boolean
     */
    public boolean isExitable() {
        return exitable;
    }

    /**
     * Method render.
     *
     * @param g Graphics2D
     */
    public void render(Graphics2D g) {
        if (exitable)
            g.drawImage(interfaceExit, (int) getExitBounds().getX(), (int) getExitBounds().getY(), null);
    }

    /**
     * Method setBounds.
     *
     * @param bounds Rectangle
     */
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     * Method setExitable.
     *
     * @param exitable boolean
     */
    public void setExitable(boolean exitable) {
        this.exitable = exitable;
    }

    /**
     * Constructor for Interface.
     *
     * @param x      int
     * @param y      int
     * @param width  int
     * @param height int
     */
    public Interface(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
        InterfaceHandler.getActiveInterfaces().add(this);
        interfaceExit = ImageLoader.loadInterface("Interface-Exit");
    }

    public Rectangle bounds;

    private boolean exitable = true;

    public boolean focused = false;

    private Image interfaceExit;

}
