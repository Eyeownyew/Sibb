package com.sibb.visual;

import com.sibb.Main;
import com.sibb.util.ImageLoader;

import java.awt.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public abstract class ScrollableInterface extends Interface {

    /**
     * Method getInnerPanel.
     *
     * @return Panel
     */
    public Panel getInnerPanel() {
        return innerPanel;
    }

    /**
     * Method getLineHeight.
     *
     * @return int
     */
    public int getLineHeight() {
        return lineHeight;
    }

    /**
     * Method getScrollbarRectangle.
     *
     * @return Rectangle
     */
    public Rectangle getScrollbarRectangle() {
        int scrollbarMaxHeight = (int) ((getBounds().getY() + getBounds().getHeight() - 36) - (getBounds()
                .getY() + 36));
        scrollbarHeight = (int) (scrollbarMaxHeight * (innerPanel.getInitialHeight() / (float) innerPanel.currentHeight));

        if (invertedScrolling) {
            yToDraw = (int) (getBounds().getY() + getBounds().getHeight() - 36 /* margin */);
            yToDraw -= scrollbarHeight;
        } else {
            yToDraw = (int) (getBounds().getY() + 36/* margin */);
        }
        if (scrollY != 0)
            if (invertedScrolling)
                yToDraw -= Math
                        .ceil(scrollbarMaxHeight
                                * (float) ((float) Math.abs(scrollY * lineHeight) / (float) innerPanel.currentHeight));
            else
                yToDraw += Math
                        .floor(scrollbarMaxHeight
                                * (float) ((float) Math.abs(scrollY * lineHeight) / (float) innerPanel.currentHeight));

        if (yToDraw < getBounds().getY() + 36)
            yToDraw = (int) (getBounds().getY() + 36);

        return new Rectangle(
                (int) (getBounds().getX() + getBounds().getWidth() - scrollbarWidth - 5)/* margin */,
                yToDraw, scrollbarWidth, scrollbarHeight - 1);

    }

    /**
     * Method getScrollY.
     *
     * @return int
     */
    public int getScrollY() {
        return scrollY;
    }

    /**
     * Method mouseWheelMoved.
     *
     * @param newValue int
     */
    public void mouseWheelMoved(int newValue) {
        int newScrollY = scrollY - (newValue / Math.abs(newValue));

        if (invertedScrolling) {
            int limit = -(getInnerPanel().getCurrentHeight() - getInnerPanel().getInitialHeight())
                    / lineHeight;

            if (newScrollY >= 0) {
                newScrollY = 0;
            } else if (newScrollY <= limit) {
                newScrollY = limit;
            }

            scrollY = newScrollY;
        } else {
            limit = ((getInnerPanel().getCurrentHeight() - getInnerPanel().getInitialHeight()) / lineHeight);

            if (newScrollY < 0)
                newScrollY = 0;
            else if (newScrollY > limit)
                newScrollY = limit;

            scrollY = newScrollY;
        }
    }

    /**
     * Method render.
     *
     * @param g Graphics2D
     */
    @Override
    public void render(Graphics2D g) {
        renderScrollbar(g);
    }

    /**
     * Method renderScrollbar.
     *
     * @param g Graphics2D
     */
    private void renderScrollbar(Graphics2D g) {
        g.drawImage(scrollbarUp,
                (int) (getBounds().getX() + getBounds().getWidth() - 19)/* margin */,
                (int) (getBounds().getY() + 21) /* margin */, null);
        g.drawImage(scrollbarTrack, (int) (getBounds().getX() + getBounds().getWidth() - 13) /* margin */,
                (int) (getBounds().getY() + 36) /* margin */, null);
        g.drawImage(scrollbarDown, (int) (getBounds().getX() + getBounds().getWidth() - 19)/* margin */,
                (int) (getBounds().getY() + getBounds().getHeight() - 36) /* margin */, null);
        Rectangle scrollbar = getScrollbarRectangle();
        g.setColor(scrollbarColor);
        g.fill(scrollbar);
        g.setColor(Color.black);
        g.draw(scrollbar);
        super.render(g);
    }

    /**
     * Method setInnerPanel.
     *
     * @param innerPanel Panel
     */
    public void setInnerPanel(Panel innerPanel) {
        this.innerPanel = innerPanel;
    }

    /**
     * Method setLineHeight.
     *
     * @param lineHeight int
     */
    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    /**
     * Method setScrollY.
     *
     * @param scrollY int
     */
    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

    /**
     * Constructor for ScrollableInterface.
     *
     * @param width             int
     * @param height            int
     * @param lineHeight        int
     * @param invertedScrolling boolean
     */
    public ScrollableInterface(int width, int height, int lineHeight, boolean invertedScrolling) {
        this((Main.app.getWidth() / 2) - (width / 2), (Main.app.getHeight() / 2) - (height / 2),
                width, height, lineHeight, invertedScrolling);
    }

    /**
     * Constructor for ScrollableInterface.
     *
     * @param x                 int
     * @param y                 int
     * @param width             int
     * @param height            int
     * @param lineHeight        int
     * @param invertedScrolling boolean
     */
    public ScrollableInterface(int x, int y, int width, int height, int lineHeight,
                               boolean invertedScrolling) {
        super(x, y, width, height);
        this.invertedScrolling = invertedScrolling;
        this.lineHeight = lineHeight;
        scrollbarTrack = ImageLoader.loadInterface("Scrollbar-track");
        scrollbarUp = ImageLoader.loadInterface("Scrollbar-up");
        scrollbarDown = ImageLoader.loadInterface("Scrollbar-down");
    }

    private Panel innerPanel;

    private boolean invertedScrolling;

    public int limit = 0;

    public int lineHeight, scrollbarHeight, scrollY, yToDraw;

    public final int margin = 1, scrollbarWidth = 14;

    private Color scrollbarColor = new Color(0x8b8f92);

    public Image scrollbarTrack, scrollbarUp, scrollbarDown;
}
