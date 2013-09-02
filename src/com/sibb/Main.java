package com.sibb;

import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	/**
	 * 
	 * @param argv
	 *            The arguments passed to the test
	 */
	public static AppGameContainer app = null;
	public static void main(String[] argv) {
		try {
			Engine engine = new Engine("Sibb");
			app = new AppGameContainer(engine, (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(),
					false);
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
