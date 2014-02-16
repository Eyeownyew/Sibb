package com.sibb;

import com.sibb.visual.UIRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public abstract class GameContainer extends JPanel {

    /**
     * Method getGraphics.
     *
     * @return Graphics2D
     */
    public Graphics2D getGraphics() {
        return graphics;
    }

    /**
     * Method getHeight.
     *
     * @return int
     */
    @Override
    public int getHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    /**
     * Method getWidth.
     *
     * @return int
     */
    @Override
    public int getWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public abstract void init();

    /**
     * Method paint.
     *
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        if (graphics == null)
            graphics = (Graphics2D) g;
        render((Graphics2D) g);
        interfaceDF.render((Graphics2D) g);
    }

    /**
     * Interface Draw Fairy
     */
    private UIRenderer interfaceDF = null;

    /**
     * Method render.
     *
     * @param g Graphics2D
     */
    public abstract void render(Graphics2D g);

    /**
     * Method setTargetFrameRate.
     *
     * @param i int
     */
    public void setTargetFrameRate(int i) {
        interval = 1000 / i;
    }

    public void start() {
        lastUpdate = System.currentTimeMillis();
        interfaceDF = new UIRenderer();
        new Thread(new DrawFairy()).start();
    }

    /**
     * Method update.
     *
     * @param delta int
     */
    public abstract void update(int delta);

    JFrame frame;

    private Graphics2D graphics;

    private long interval = 0L;

    int x = 0;
    double lastUpdate = 0L;

    /**
     * @author Eyeownywe
     * @version $Revision: 1.0 $
     */
    class DrawFairy implements Runnable {

        /**
         * Method run.
         *
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            while (true) {
                if (System.currentTimeMillis() - lastUpdate > interval) {
                    lastUpdate = System.currentTimeMillis();
                    loop();
                }
            }
        }

        void loop() {
            Window.getFrame().repaint();
            update((int) (System.currentTimeMillis() - lastUpdate));
            lastUpdate = System.currentTimeMillis();
        }

        double lastUpdate = 0L;

    }
}
