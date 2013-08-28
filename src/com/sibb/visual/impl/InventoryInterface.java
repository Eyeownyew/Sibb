package com.sibb.visual.impl;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.sibb.util.ImageLoader;
import com.sibb.visual.Panel;
import com.sibb.visual.ScrollableInterface;

public class InventoryInterface extends ScrollableInterface {
	private Image chatboxImage;
	public InventoryInterface(int width, int height) {
		super(width, height, 40, false);
		chatboxImage = ImageLoader.loadInterface("cb_bg");
		setInnerPanel(new Panel(20 * lineHeight, 5 * lineHeight));
		getInnerPanel().setCurrentHeight(20 * lineHeight);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(chatboxImage, getBounds().getX(), getBounds().getY());
		super.render(g);
		g.setColor(Color.white);
		g.drawString("limit " + super.limit, 20, 400);
		g.drawString("ytodraw " + super.yToDraw, 20, 420);
	}
	
	@Override
	public Rectangle getExitBounds() {
		return new Rectangle(getBounds().getWidth()+getBounds().getX()-4-15, getBounds().getY()+4, 15, 15);
	}

}
