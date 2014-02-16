package com.sibb;

import com.sibb.input.Keyboard;
import com.sibb.input.Mouse;

import javax.swing.*;
import java.awt.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Window {
    static JFrame frame = null;

    static GameContainer gameContainer = null;

    /**
     * Method getContainer.
     *
     * @return GameContainer
     */
    public static GameContainer getContainer() {
        return gameContainer;
    }

    /**
     * Method getFrame.
     *
     * @return JFrame
     */
    public static JFrame getFrame() {
        return frame;
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
     * Method getWidth.
     *
     * @return int
     */
    public int getWidth() {
        return width;
    }

    public void requestExit() {
        System.exit(-1);
    }

    /**
     * Constructor for Window.
     *
     * @param name       String
     * @param width      int
     * @param height     int
     * @param borderless boolean
     */
    public Window(final String name, int width, int height, boolean borderless) {
        camera = new Camera();
        this.width = width;
        this.height = height;
        frame = new JFrame(name);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameContainer = new Engine();
        frame.add(gameContainer);
        gameContainer.setFocusable(true);
        gameContainer.setTargetFrameRate(60);
        gameContainer.addKeyListener(new Keyboard());
        Mouse mouse = new Mouse();
        gameContainer.addMouseListener(mouse);
        gameContainer.addMouseMotionListener(mouse);
        gameContainer.addMouseWheelListener(mouse);
        if (borderless) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        } else {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.getDefaultScreenDevice().setFullScreenWindow(frame);
        }
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setBackground(Color.black);
        gameContainer.init();
        gameContainer.start();
    }

    private int width, height;

    private static Camera camera;

    /**
     * Method getCamera.
     *
     * @return Camera
     */
    public static Camera getCamera() {
        return camera;
    }
}
