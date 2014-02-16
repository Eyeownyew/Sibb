package com.sibb.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Justin
 * @version $Revision: 1.0 $
 */
public class WzFont {

    private static final String DEFAULT_FONT = "Ubuntu-R";

    private static final float DEFAULT_SIZE = 14;

    private static WzFont instance;

    /**
     * Method getDefaultFontSize.
     *
     * @return float
     */
    public static float getDefaultFontSize() {
        return DEFAULT_SIZE;
    }

    /**
     * Method getInstance.
     *
     * @return WzFont
     */
    public static WzFont getInstance() {
        if (instance == null)
            return new WzFont(DEFAULT_FONT, DEFAULT_SIZE);
        return instance;
    }

    /**
     * Method getStringWidth.
     *
     * @param g      Graphics2D
     * @param string String
     * @return int
     */
    public static int getStringWidth(Graphics2D g, String string) {
        return g.getFontMetrics().stringWidth(string);
    }

    /**
     * Method createFont.
     *
     * @param name String
     * @param size float
     */
    public void createFont(String name, float size) {
        try {
            font = (Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/" + name + ".ttf")))
                    .deriveFont(14f);
            fontSize = size;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method getFont.
     *
     * @return Font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Constructor for WzFont.
     *
     * @param name String
     * @param size float
     */
    public WzFont(String name, float size) {
        instance = this;
        createFont(name, size);
    }

    /**
     * Variables
     */
    Font font = null;

    public float fontSize;
}
