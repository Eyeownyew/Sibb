package com.sibb.visual;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

import com.sibb.visual.impl.ChatboxInterface;
import com.sibb.visual.impl.InventoryInterface;

public class InterfaceHandler {

	public static List<Interface> activeInterfaces = new ArrayList<Interface>();

	public static List<Interface> getActiveInterfaces() {
		return activeInterfaces;
	}

	public static void setFocused(Interface i) {
		activeInterfaces.remove(i);
		activeInterfaces.add(0, i);
	}

	public static Interface getFocused() {
		return activeInterfaces.get(0);
	}

	public static void renderInterfaces(Graphics g) {
		for (int i = activeInterfaces.size() - 1; i >= 0; i--) {
			activeInterfaces.get(i).render(g);
		}
	}

	private static InventoryInterface inventory;

	public static InventoryInterface getInventory() {
		return inventory;
	}

	public static void setInventory(InventoryInterface inventory) {
		InterfaceHandler.inventory = inventory;
	}

	private static ChatboxInterface chatbox;

	public static ChatboxInterface getChatbox() {
		return chatbox;
	}

	public static void setChatbox(ChatboxInterface chatbox) {
		InterfaceHandler.chatbox = chatbox;
	}

}
