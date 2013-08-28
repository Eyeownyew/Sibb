package com.sibb.state;

import java.io.DataOutputStream;

import org.jboss.netty.channel.Channel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;

import com.sibb.Client;
import com.sibb.Engine;
import com.sibb.Main;

public class LoginState extends State {

	String errorMessage = "";
	String errorMessage2 = "";
	UnicodeFont font = null;
	boolean messageSent = false;
	DataOutputStream out = null;
	String password = "";
	int selectedBox = 0;

	String username = "";

	private void attemptLogin() {
		if (username.length() < 2 || password.length() < 6) {
			errorMessage = "Your username must be at least 2 characters and less than 12 characters,";
			errorMessage2 = "and your password must be at least 6 characters, but less than 16 characters.";
			return;
		}
		Client c = Client.getClient();
		c.setUsername(username);
		c.setPassword(password);
		try {
			Channel channel = c.getChannel();
			channel.write(c.getConstructor().loginPacket());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Engine.getInstance().setState(new GameState(), Main.app);
	}

	@Override
	public String getID() {
		return "login";
	}

	/**
	 * @param args
	 */

	@Override
	public void init(GameContainer gc) throws SlickException {
		font = Engine.getInstance().getFont();
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			Main.app.exit();
			break;
		case Input.KEY_BACK:
			if (selectedBox == 0 && username.length() > 0)
				username = username.substring(0, username.length() - 1);
			else if (selectedBox == 1 && password.length() > 0)
				password = password.substring(0, password.length() - 1);
			break;
		case Input.KEY_TAB:
			if (selectedBox == 0)
				selectedBox = 1;
			else
				selectedBox = 0;
			break;
		case Input.KEY_ENTER:
			if (selectedBox == 0)
				selectedBox = 1;
			else if (selectedBox == 1)
				attemptLogin();
			break;
		default:
			if (c > 0 && c < 255)
				if (selectedBox == 0 && username.length() < 12)
					username += c;
				else if (selectedBox == 1 && password.length() < 16)
					password += c;
		}
	}

	public void mouseClicked(int button, int x, int y, int clickCount) {
		GameContainer container = Main.app;
		if (button == 0) {
			if (x > (container.getWidth() / 2) - (font.getWidth("Login") / 2) - 200
					&& x < (container.getWidth() / 2) - (font.getWidth("Login") / 2) + 200
					&& y < (container.getHeight() / 2) + 120
					&& y > (container.getHeight() / 2) + 100) {
				attemptLogin();
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		int width = 400;
		int height = 20;
		g.setColor(Color.darkGray);
		g.setFont(font);
		g.fill(new Rectangle((gc.getWidth() / 2) - (width / 2), (gc.getHeight() / 2) - 100, width,
				height));
		g.fill(new Rectangle((gc.getWidth() / 2) - (width / 2), (gc.getHeight() / 2), width, height));
		g.fill(new Rectangle((gc.getWidth() / 2) - (width / 2), (gc.getHeight() / 2) + 100, width,
				height));
		g.setColor(Color.yellow);
		g.drawString(username, (gc.getWidth() / 2) - (font.getWidth(username) / 2),
				(gc.getHeight() / 2) - 100);
		String passwordBlur = "";
		for (int i = 0; i < password.length(); i++)
			passwordBlur += "*";
		g.drawString(passwordBlur, (gc.getWidth() / 2) - (font.getWidth(passwordBlur) / 2),
				(gc.getHeight() / 2));
		g.setColor(Color.blue);
		g.drawString("Login", (gc.getWidth() / 2) - (font.getWidth("Login") / 2),
				(gc.getHeight() / 2) + 100);
		g.setColor(Color.red);
		g.drawString(errorMessage, (gc.getWidth() / 2) - (font.getWidth(errorMessage) / 2),
				(gc.getHeight() / 2) - 200);
		g.drawString(errorMessage2, (gc.getWidth() / 2) - (font.getWidth(errorMessage2) / 2),
				(gc.getHeight() / 2) - 180);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
	}

}
