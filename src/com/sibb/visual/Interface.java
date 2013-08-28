package com.sibb.visual;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.sibb.Main;
import com.sibb.util.ImageLoader;

public abstract class Interface {

	public boolean focused = false;
	public Rectangle bounds;
	private Image interfaceExit;
	private boolean exitable = true;

	public boolean isExitable() {
		return exitable;
	}

	public void setExitable(boolean exitable) {
		this.exitable = exitable;
	}

	public Interface(int width, int height) {
		this((Main.app.getWidth() / 2) - (width / 2), (Main.app.getHeight() / 2) - (height / 2),
				width, height);
	}

	public Interface(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x, y, width, height);
		interfaceExit = ImageLoader.loadInterface("Interface-Exit");
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void render(Graphics g) {
		if (exitable)
			g.drawImage(interfaceExit, getExitBounds().getX(), getExitBounds().getY());
	}

	public Rectangle getExitBounds() {
		return null;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}
