package com.sibb;

import com.sibb.net.Connection;
import com.sibb.state.GameState;
import com.sibb.state.LoginState;
import com.sibb.state.State;
import com.sibb.util.WzFont;

import java.awt.*;

/**
 * @author Eyeownyew
 * @version $Revision: 1.0 $
 */
public class Engine extends GameContainer {

    private static Engine engine;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method getInstance
     *
     * @return Engine
     */
    public static Engine getInstance() {
        return engine;
    }

    /**
     * Method getFont.
     *
     * @return Font * @see java.awt.MenuContainer#getFont() * @see java.awt.MenuContainer#getFont()
     */
    public Font getFont() {
        return font;
    }

    /**
     * Method getGState.
     *
     * @return State
     */
    public State getGState() {
        return state;
    }

    @Override
    public void init() {
        engine = this;
        setState(new LoginState());
        new Thread(new Connection()).start();
        font = WzFont.getInstance().getFont();
    }

    public void loggedIn() {
        loggedIn = true;
    }

    /**
     * Method loop
     *
     * @param delta int
     */
    void loop(int delta) {
        state.update(this, delta);
        if (loggedIn) {
            setState(new GameState());
            loggedIn = false;
        }
    }

    /**
     * Method render
     *
     * @param g Graphics2D
     */
    @Override
    public void render(Graphics2D g) {
        g.setFont(font);
        if (state != null && state.init)
            state.render(this, g);
    }

    /**
     * Method setState
     *
     * @param gameState State
     */
    public void setState(State gameState) {
        this.state = gameState;
        gameState.init(this);
    }

    /**
     * Method update
     *
     * @param delta int
     */
    public void update(int delta) {
        loop(delta);
    }

    Font font = null;

    private boolean loggedIn = false;

    private State state;


}