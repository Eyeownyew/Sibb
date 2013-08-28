package com.sibb;

import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;

import com.sibb.net.Connection;
import com.sibb.state.LoginState;
import com.sibb.state.State;
import com.sibb.util.WzFont;
import com.sibb.visual.Interface;
import com.sibb.visual.InterfaceHandler;
import com.sibb.visual.ScrollableInterface;

public class Engine extends BasicGame {

	private static Engine engine;

	public static Engine getInstance() {
		return engine;
	}

	UnicodeFont font = null;

	public final int fontSize = 14;

	private State state;

	public Engine(String name) {
		super(name);
		engine = this;
	}

	public List<Interface> getActiveInterfaces() {
		return InterfaceHandler.getActiveInterfaces();
	}

	public UnicodeFont getFont() {
		return font;
	}

	public State getState() {
		return state;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Connection.establishConnection();
		font = WzFont.rsct(fontSize);
		setState(new LoginState(), gc);
		gc.getInput().enableKeyRepeat();
	}

	@Override
	public void keyPressed(int key, char c) {
		state.keyPressed(key, c);
	}

	void loop(GameContainer gc, int delta) {

	}

	private boolean interfaceFocused = false;

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		mouseClicked(0, oldX, oldY, 1);
		Interface i = InterfaceHandler.getFocused();
		if(i.getBounds().contains(oldX,oldY)) {
			Rectangle r = i.getBounds();
			i.setBounds(new Rectangle(r.getX()+(newX-oldX), r.getY()+(newY-oldY), r.getWidth(), r.getHeight()));
		}
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		for (Interface i : getActiveInterfaces()) {
			if (i.getBounds().contains(x, y)) {
				if (InterfaceHandler.getFocused() != i)
					InterfaceHandler.setFocused(i);
				interfaceFocused = true;
				break;
			}
			interfaceFocused = false;
		}
		state.mouseClicked(button, x, y, clickCount);
	}

	@Override
	public void mouseWheelMoved(int newValue) {
		if (interfaceFocused) {
			Interface i = InterfaceHandler.getFocused();
			if (i instanceof ScrollableInterface) {
				((ScrollableInterface) i).mouseWheelMoved(newValue);
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		getState().render(gc, g);
	}

	public void setFont(UnicodeFont font) {
		this.font = font;
	}

	public void setState(State gameState, GameContainer gc) {
		try {
			gameState.init(gc);
			this.state = gameState;
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		loop(gc, delta);
		getState().update(gc, delta);
	}
}