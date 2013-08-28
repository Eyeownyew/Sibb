package com.sibb.visual;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

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
		for (int i = activeInterfaces.size()-1; i >= 0; i--) {
			activeInterfaces.get(i).render(g);
		}
	}
}
