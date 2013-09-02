package com.sibb.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.tiled.TiledMap;

import com.sibb.Client;
import com.sibb.Engine;
import com.sibb.Main;
import com.sibb.visual.InterfaceHandler;
import com.sibb.visual.impl.ChatboxInterface;
import com.sibb.visual.impl.InventoryInterface;
import com.sibb.world.Chat;

public class GameState extends State {

	private boolean displayMinimap;
	UnicodeFont font = null;

	TiledMap map = null;

	Graphics mapGraphics = null;

	private Image minimap;

	private boolean minimapCreated = false;

	public String playerEnteredText = "";

	private void createMinimap() {
		int mapWidth = (map.getWidth() * map.getTileWidth());
		int mapHeight = (map.getHeight() * map.getTileHeight());
		try {
			minimap = new Image(mapWidth, mapHeight);
			mapGraphics = minimap.getGraphics();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		for (int x = 0; x < map.getWidth(); x++)
			for (int y = 0; y < map.getHeight(); y++)
				for (int z = 0; z < map.getLayerCount(); z++)
					if (map.getTileImage(x, y, z) != null)
						mapGraphics.drawImage(map.getTileImage(x, y, z), x * 32, y * 32, 0, 0, 32,
								32);
		minimap = minimap.getScaledCopy((int) (Main.app.getWidth() * .25f),
				(int) (Main.app.getHeight() * .25f));
		minimap.setAlpha(.9f);
		mapGraphics.flush();
		minimapCreated = true;
	}

	public void drawString(Graphics g, String s, int x, int y) {
		g.drawString(s, x, y);
	}

	public void drawStringCentered(Graphics g, String s, int x, int y) {
		drawString(g, s, x - (font.getWidth(s) / 2), y);
	}

	private void exit() {
		Main.app.exit();
	}

	@Override
	public String getID() {
		return "Game";
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		font = Engine.getInstance().getFont();
		map = new TiledMap("data/maps/betamap.tmx");
		gc.getInput().enableKeyRepeat();
		initInterfaces();
	}

	private void initInterfaces() {
		new ChatboxInterface(0, Main.app.getHeight() - 240, 600, 240,
				Engine.getInstance().fontSize + 2);
		new InventoryInterface(600, 240);
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			exit();
			break;
		case Input.KEY_F8:
			if (!minimapCreated)
				createMinimap();
			displayMinimap = !displayMinimap;
			break;
		case Input.KEY_BACK:
			if (playerEnteredText.length() > 0)
				playerEnteredText = playerEnteredText.substring(0, playerEnteredText.length() - 1);
			break;
		case Input.KEY_ENTER:
			if (playerEnteredText != "") {
				Chat.messageEntered(playerEnteredText);
				playerEnteredText = "";
			}
			break;
		case Input.KEY_UP:
			Client.getPlayer().setY(Client.getPlayer().getY() + 10);
			break;
		case Input.KEY_DOWN:
			Client.getPlayer().setY(Client.getPlayer().getY() - 10);
			break;
		case Input.KEY_LEFT:
			Client.getPlayer().setX(Client.getPlayer().getX() - 10);
			break;
		case Input.KEY_RIGHT:
			Client.getPlayer().setX(Client.getPlayer().getX() + 10);
			break;
		default:
			if (c > 0 && c < 255) {
				for (char invalid : invalidCharacters)
					if (c == invalid)
						return;
				if (font.getWidth(playerEnteredText + c) + 20 < 587 - font.getWidth(Client
						.getPlayer().getUsername() + ":"))
					playerEnteredText += c;
			}
		}
	}

	private char[] invalidCharacters = new char[] { '<', '>' };

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(-Client.getPlayer().getX(), Client.getPlayer().getY());
		g.setColor(Color.white);
		g.drawString("pX: " + Client.getPlayer().getX() + ", pY: " + Client.getPlayer().getY(), 20,
				200);
		if (displayMinimap) {
			g.setColor(new Color(Color.black.r, Color.black.g, Color.black.b, .9f));
			g.fillRect(0, 0, Main.app.getWidth(), Main.app.getHeight());
			minimap.drawCentered(gc.getWidth() / 2, gc.getHeight() / 2);
			g.setColor(Color.white);
			drawStringCentered(g, "Minimap", gc.getWidth() / 2, 20);
		}
		InterfaceHandler.renderInterfaces(g);

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {

	}

}
