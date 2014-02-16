package com.sibb.state;

import com.sibb.GameContainer;

import java.awt.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public abstract class State {

    /**
     * Method getID.
     *
     * @return String
     */
    public abstract String getID();

    /**
     * Method init.
     *
     * @param gc GameContainer
     */
    public abstract void init(GameContainer gc);

    /**
     * Method mouseClicked.
     *
     * @param button     int
     * @param x          int
     * @param y          int
     * @param clickCount int
     */
    public abstract void mouseClicked(int button, int x, int y, int clickCount);

    /**
     * Method render.
     *
     * @param gc GameContainer
     * @param g  Graphics2D
     */
    public abstract void render(GameContainer gc, Graphics2D g);

    /**
     * Method update.
     *
     * @param gc    GameContainer
     * @param delta int
     */
    public abstract void update(GameContainer gc, int delta);

    public boolean init = false;
}
