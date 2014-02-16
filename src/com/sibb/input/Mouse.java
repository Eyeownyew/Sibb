package com.sibb.input;

import com.sibb.Engine;
import com.sibb.visual.Interface;
import com.sibb.visual.InterfaceHandler;
import com.sibb.visual.ScrollableInterface;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Mouse extends MouseAdapter implements MouseWheelListener {

    /**
     * Method mouseClicked.
     *
     * @param event MouseEvent
     * @see java.awt.event.MouseListener#mouseClicked(MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        int x = event.getX(), y = event.getY();
        int button = event.getButton(), clickCount = event.getClickCount();

        Engine.getInstance().getGState().mouseClicked(button, x, y, clickCount);
    }

    /**
     * Method mouseDragged.
     *
     * @param e MouseEvent
     * @see java.awt.event.MouseMotionListener#mouseDragged(MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        int newX = e.getX(), newY = e.getY();
        Interface i = InterfaceHandler.getFocused();
        if (i == null)
            return;
        if (i.getBounds().contains(mouseX, mouseY)) {
            Rectangle r = i.getBounds();
            r.x += newX - mouseX;
            r.y += newY - mouseY;
            i.setBounds(r);
        }
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * Method mouseMoved.
     *
     * @param e MouseEvent
     * @see java.awt.event.MouseMotionListener#mouseMoved(MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * Method mousePressed.
     *
     * @param e MouseEvent
     * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (Interface i : InterfaceHandler.getActiveInterfaces()) {
            if (i.getBounds().contains(e.getX(), e.getY())) {
                if (InterfaceHandler.getFocused() != i)
                    InterfaceHandler.setFocused(i);
                break;
            }
        }
    }

    /**
     * Method mouseReleased.
     *
     * @param e MouseEvent
     * @see java.awt.event.MouseListener#mouseReleased(MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Method mouseWheelMoved.
     *
     * @param event MouseWheelEvent
     * @see java.awt.event.MouseWheelListener#mouseWheelMoved(MouseWheelEvent)
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        Interface i = InterfaceHandler.getFocused();
        if (i != null)
            if (i instanceof ScrollableInterface) {
                ScrollableInterface i1 = (ScrollableInterface) i;
                i1.mouseWheelMoved(-event.getUnitsToScroll());
            }
    }

    private int mouseX, mouseY;

}