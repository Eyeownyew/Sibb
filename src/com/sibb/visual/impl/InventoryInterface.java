package com.sibb.visual.impl;

import com.sibb.util.ImageLoader;
import com.sibb.visual.Panel;
import com.sibb.visual.ScrollableInterface;

import java.awt.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class InventoryInterface extends ScrollableInterface {
    /**
     * Method getExitBounds.
     *
     * @return Rectangle
     */
    @Override
    public Rectangle getExitBounds() {
        return new Rectangle((int) (getBounds().getWidth() + getBounds().getX() - 4 - 15), (int) (getBounds().getY() + 4), 15, 15);
    }

    /**
     * Method render.
     *
     * @param g Graphics2D
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(chatboxImage, (int) getBounds().getX(), (int) getBounds().getY(), null);
        super.render(g);
        g.setColor(Color.white);
        g.drawString("limit " + super.limit, 20, 400);
        g.drawString("ytodraw " + super.yToDraw, 20, 420);
    }

    /**
     * Constructor for InventoryInterface.
     *
     * @param width  int
     * @param height int
     */
    public InventoryInterface(int width, int height) {
        super(width, height, 40, false);
        chatboxImage = ImageLoader.loadInterface("cb_bg");
        setInnerPanel(new Panel(20 * lineHeight, 5 * lineHeight));
        getInnerPanel().setCurrentHeight(20 * lineHeight);
    }

    private Image chatboxImage;

}
