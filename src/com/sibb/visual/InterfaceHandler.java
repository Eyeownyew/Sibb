package com.sibb.visual;

import com.sibb.visual.impl.ChatboxInterface;
import com.sibb.visual.impl.InventoryInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class InterfaceHandler {

    public static List<Interface> activeInterfaces = new ArrayList<Interface>();

    private static ChatboxInterface chatbox;

    private static InventoryInterface inventory;

    /**
     * Method getActiveInterfaces.
     *
     * @return List<Interface>
     */
    public static List<Interface> getActiveInterfaces() {
        return activeInterfaces;
    }

    /**
     * Method getChatbox.
     *
     * @return ChatboxInterface
     */
    public static ChatboxInterface getChatbox() {
        return chatbox;
    }

    /**
     * Method getFocused.
     *
     * @return Interface
     */
    public static Interface getFocused() {
        if (activeInterfaces.size() > 0)
            return activeInterfaces.get(0);
        else
            return null;
    }

    /**
     * Method getInventory.
     *
     * @return InventoryInterface
     */
    public static InventoryInterface getInventory() {
        return inventory;
    }

    /**
     * Method renderInterfaces.
     *
     * @param g Graphics2D
     */
    public static void renderInterfaces(Graphics2D g) {
        for (int i = activeInterfaces.size() - 1; i >= 0; i--) {
            activeInterfaces.get(i).render(g);
        }
    }

    /**
     * Method setChatbox.
     *
     * @param chatbox ChatboxInterface
     */
    public static void setChatbox(ChatboxInterface chatbox) {
        InterfaceHandler.chatbox = chatbox;
    }

    /**
     * Method setFocused.
     *
     * @param i Interface
     */
    public static void setFocused(Interface i) {
        activeInterfaces.remove(i);
        activeInterfaces.add(0, i);
    }

    /**
     * Method setInventory.
     *
     * @param inventory InventoryInterface
     */
    public static void setInventory(InventoryInterface inventory) {
        InterfaceHandler.inventory = inventory;
    }

}
