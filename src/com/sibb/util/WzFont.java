package com.sibb.util;

import java.awt.Color;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.ShadowEffect;

/**
 * @author Justin
 * 
 */
public class WzFont extends UnicodeFont {
	public static WzFont rsct(int size) throws SlickException {
		WzFont font = new WzFont("Ubuntu-R", size, false, false);
		ShadowEffect shadow = new ShadowEffect(Color.black, 0, 0, .9f);
		shadow.setBlurKernelSize(2);
		font.getEffects().add(shadow);
		font.getEffects().add(new ColorEffect(Color.white));
		font.loadGlyphs();
		return font;
	}
	boolean fontBold = false;
	Color fontColor = Color.black;
	boolean fontItalic = false;
	String fontName = "rsct";

	int fontSize = 12;

	/**
	 * Constructors
	 * 
	 * @throws SlickException
	 */
	public WzFont() throws SlickException {
		this("rsct", 12, false, false, Color.black);
	}

	public WzFont(String name, int size, boolean bold, boolean italic)
			throws SlickException {
		super("data/fonts/" + name + ".ttf", size, bold, italic);
		fontSize = size;
		fontName = name;
		fontBold = bold;
		fontItalic = italic;
		addAsciiGlyphs();
	}

	public WzFont(String name, int size, boolean bold, boolean italic,
			Color color) throws SlickException {
		super("data/fonts/" + name + ".ttf", size, bold, italic);
		fontSize = size;
		fontName = name;
		fontBold = bold;
		fontItalic = italic;
		fontColor = color;
		addAsciiGlyphs();
		getEffects().add(new ColorEffect(color));
		loadGlyphs();
	}

	public WzFont setColor(Color color) throws SlickException {
		return new WzFont(fontName, fontSize, fontBold, fontItalic, color);
	}

	/**
	 * Variables
	 */

}
