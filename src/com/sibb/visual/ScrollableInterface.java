package com.sibb.visual;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.sibb.Main;
import com.sibb.util.ImageLoader;

public abstract class ScrollableInterface extends Interface {

	private Panel innerPanel;

	public int lineHeight, scrollbarHeight, scrollY, yToDraw;

	public final int margin = 1, scrollbarWidth = 15;

	public Image scrollbarTrack, scrollbarUp, scrollbarDown;

	private boolean invertedScrolling;

	private Color scrollbarColor = new Color(0x8b8f92);

	public ScrollableInterface(int width, int height, int lineHeight, boolean invertedScrolling) {
		this((Main.app.getWidth() / 2) - (width / 2), (Main.app.getHeight() / 2) - (height / 2),
				width, height, lineHeight, invertedScrolling);
	}

	public ScrollableInterface(int x, int y, int width, int height, int lineHeight,
			boolean invertedScrolling) {
		super(x, y, width, height);
		this.invertedScrolling = invertedScrolling;
		this.lineHeight = lineHeight;
		scrollbarTrack = ImageLoader.loadInterface("Scrollbar-track");
		scrollbarUp = ImageLoader.loadInterface("Scrollbar-up");
		scrollbarDown = ImageLoader.loadInterface("Scrollbar-down");
	}

	public Panel getInnerPanel() {
		return innerPanel;
	}

	public int getLineHeight() {
		return lineHeight;
	}

	public int getScrollY() {
		return scrollY;
	}

	public int limit = 0;

	public void mouseWheelMoved(int newValue) {
		if (invertedScrolling) {
			int newScrollY = scrollY - (newValue / 120);
			int limit = -(getInnerPanel().getCurrentHeight() - getInnerPanel().getInitialHeight())
					/ lineHeight;

			if (newScrollY >= 0) {
				newScrollY = 0;
			} else if (newScrollY <= limit) {
				newScrollY = limit;
			}

			scrollY = newScrollY;
		} else {
			int newScrollY = scrollY - (newValue / 120);
			limit = ((getInnerPanel().getCurrentHeight() - getInnerPanel().getInitialHeight()) / lineHeight);

			if (newScrollY < 0)
				newScrollY = 0;
			else if (newScrollY > limit)
				newScrollY = limit;

			scrollY = newScrollY;
		}
	}

	@Override
	public void render(Graphics g) {
		renderScrollbar(g);
	}

	private void renderScrollbar(Graphics g) {
		g.drawImage(scrollbarUp, getBounds().getX() + getBounds().getWidth() - 19/* margin */,
				getBounds().getY() + 21 /* margin */);
		g.drawImage(scrollbarTrack, getBounds().getX() + getBounds().getWidth() - 13 /* margin */,
				getBounds().getY() + 36 /* margin */);
		g.drawImage(scrollbarDown, getBounds().getX() + getBounds().getWidth() - 19/* margin */,
				getBounds().getY() + getBounds().getHeight() - 36 /* margin */);

		Rectangle scrollbar = getScrollbarRectangle();
		g.setColor(scrollbarColor);
		g.fill(scrollbar);
		g.setColor(Color.black);
		g.draw(scrollbar);
		super.render(g);
	}

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
				getBounds().getX() + getBounds().getWidth() - scrollbarWidth - 3/* margin */,
				yToDraw, scrollbarWidth, scrollbarHeight);

	}

	public void setInnerPanel(Panel innerPanel) {
		this.innerPanel = innerPanel;
	}

	public void setLineHeight(int lineHeight) {
		this.lineHeight = lineHeight;
	}

	public void setScrollY(int scrollY) {
		this.scrollY = scrollY;
	}
}
