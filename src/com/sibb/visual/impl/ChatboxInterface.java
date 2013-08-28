package com.sibb.visual.impl;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;

import com.sibb.Client;
import com.sibb.Engine;
import com.sibb.global.Globals;
import com.sibb.state.GameState;
import com.sibb.util.ImageLoader;
import com.sibb.visual.Panel;
import com.sibb.visual.ScrollableInterface;

public class ChatboxInterface extends ScrollableInterface {

	private Image chatboxImage;

	public ChatboxInterface(int x, int y, int width, int height, int lineHeight) {
		super(x, y, width, height, lineHeight, true);
		chatboxImage = ImageLoader.loadInterface("cb_bg");
		setInnerPanel(new Panel(2000, 15 * lineHeight));
	}

	public void drawChat(Graphics g) {
		GameState state = ((GameState) Engine.getInstance().getState());
		List<String> cbText = Globals.cbText;
		UnicodeFont font = Engine.getInstance().getFont();
		g.setFont(font);

		if (cbText.size() > 15)
			getInnerPanel().setCurrentHeight(cbText.size() * getLineHeight());

		int inputTextHeight = (int) (getBounds().getY() + getBounds().getHeight() - 18);
		g.setColor(Color.yellow);
		g.drawString(Client.getPlayer().getUsername() + ": ", getBounds().getX() + 5,
				inputTextHeight);

		g.setColor(Color.white);
		g.drawString(state.playerEnteredText + "|",
				getBounds().getX() + 5 + font.getWidth(Client.getPlayer().getUsername() + ": "),
				inputTextHeight);

		g.setClip((int) getBounds().getX() + 3, (int) getBounds().getY() + 3, (int) (getBounds()
				.getWidth() - 20), 216);

		int initialY = (int) inputTextHeight - g.getFont().getHeight("j") - 3;
		for (int i = 0; i < cbText.size(); i++)
			g.drawString(
					cbText.get(i),
					getBounds().getX() + 5,
					initialY
							- (((cbText.size() - i - 1) * getLineHeight()) + (getScrollY() * getLineHeight())));
		g.clearClip();
	}

	public void messageReceived(String message) {
		List<String> cbText = Globals.cbText;
		cbText.add(message);
		if (cbText.size() > 100) {
			cbText.remove(0);
			setScrollY(getScrollY() + 1);
		}
		if (getScrollY() != 0 && getScrollY() > -100) {
			setScrollY(getScrollY() - 1);
		}
	}

	@Override
	public void render(Graphics g) {
		drawChatboxInterface(g);
		super.render(g);
		drawChat(g);
	}

	private void drawChatboxInterface(Graphics g) {
		g.drawImage(chatboxImage, getBounds().getX(), getBounds().getY());
	}

	@Override
	public Rectangle getExitBounds() {
		return new Rectangle(getBounds().getWidth() + getBounds().getX() - 4 - 15, getBounds()
				.getY() + 4, 15, 15);
	}

}
