package com.sibb.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class State {

	public String getID() {
		return null;
	}

	public abstract void init(GameContainer gc) throws SlickException;

	public abstract void keyPressed(int key, char c);

	public abstract void mouseClicked(int button, int x, int y, int clickCount);

	public abstract void render(GameContainer gc, Graphics g) throws SlickException;

	public abstract void update(GameContainer gc, int delta) throws SlickException;
}
