package com.sibb.visual.impl;

import com.sibb.Client;
import com.sibb.Engine;
import com.sibb.Main;
import com.sibb.net.packet.type.Message;
import com.sibb.state.GameState;
import com.sibb.util.ImageLoader;
import com.sibb.util.WzFont;
import com.sibb.visual.InterfaceHandler;
import com.sibb.visual.Panel;
import com.sibb.visual.ScrollableInterface;
import com.sibb.world.Chat;

import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class ChatboxInterface extends ScrollableInterface {

    /**
     * Method colorForChatType.
     *
     * @param chatType int
     * @return Color
     */
    private Color colorForChatType(int chatType) {
        switch (chatType) {
            case GLOBAL_TEXT:
                return globalTextColor;
            case SHOUT_TEXT:
                return shoutTextColor;
            case BUDDY_TEXT:
                return buddyTextColor;
            case PLAYER_INPUT:
                return playerInputColor;
            case PLAYER_NAME:
                return playerNameColor;
            case WHISPER_TEXT:
                return whisperTextColor;
            case OTHER_PLAYER_NAME:
                return otherPlayerNameColor;
        }
        return null;
    }

    /**
     * Method colorForText.
     *
     * @param substring String
     * @return Color
     */
    private Color colorForText(String substring) {
        String s = substring;
        if (!s.startsWith("0x"))
            s = "0x" + s;
        return new Color(Integer.decode(substring));
    }

    /**
     * Method componentToHex.
     *
     * @param c int
     * @return String
     */
    String componentToHex(int c) {
        String hex = Integer.toHexString(c);
        return hex.length() == 1 ? "0" + hex : hex;
    }

    /**
     * Method drawChat.
     *
     * @param g Graphics2D
     */
    public void drawChat(Graphics2D g) {
        GameState state = ((GameState) Engine.getInstance().getGState());
        List<Message> cbText = Chat.getCbText();

        if (cbText.size() > 15)
            getInnerPanel().setCurrentHeight(cbText.size() * getLineHeight());

        int inputTextHeight = (int) (getBounds().getY() + getBounds().getHeight() - 5);
        g.setColor(Color.white);

        drawColoredText(g, Client.getClient().getUsername() + ": ", PLAYER_NAME,
                (int) (getBounds().getX()) + 5, inputTextHeight);

        drawColoredText(
                g,
                state.playerEnteredText
                        + (GameState.getInstance().chatSelected ? "|"
                        : "<0xbdbdbd>[Press enter to start typing]"),
                PLAYER_INPUT,
                (int) (getBounds().getX()) + 5
                        + WzFont.getStringWidth(g, Client.getClient().getUsername() + ": "),
                inputTextHeight);

        g.setClip((int) getBounds().getX() + 3, (int) getBounds().getY() + 3, (int) (getBounds()
                .getWidth() - 23), 216);
        int initialY = (int) inputTextHeight - (int) WzFont.getDefaultFontSize() - 7;
        for (int i = 0; i < cbText.size(); i++) {
            drawColoredText(
                    g,
                    "<" + textForColor(colorForChatType(OTHER_PLAYER_NAME)) + ">"
                            + cbText.get(i).getUsername() + ": " + "<"
                            + textForColor(colorForChatType(SHOUT_TEXT)) + ">"
                            + cbText.get(i).getMessage(),
                    cbText.get(i).getType(),
                    (int) (getBounds().getX()) + 5,
                    initialY
                            - (((cbText.size() - i - 1) * getLineHeight()) + (getScrollY() * getLineHeight())));
        }
        g.setClip(0, 0, Main.app.getWidth(), Main.app.getHeight());
    }

    /**
     * Method drawChatboxInterface.
     *
     * @param g Graphics2D
     */
    private void drawChatboxInterface(Graphics2D g) {
        g.drawImage(chatboxImage, (int) getBounds().getX(), (int) getBounds().getY(), null);
    }

    /**
     * Method drawColoredText.
     *
     * @param g        Graphics2D
     * @param text     String
     * @param chatType int
     * @param startX   int
     * @param y        int
     */
    private void drawColoredText(Graphics2D g, String text, int chatType, int startX, int y) {

        int currentX = startX;

        g.setColor(colorForChatType(chatType));

        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(text);
        int foundMatches = 0;
        int[] colorIndexes = new int[20];
        Color[] colors = new Color[20];
        int totalSkippedCharacters = 0;
        while (matcher.find()) {
            colorIndexes[foundMatches] = matcher.start() - totalSkippedCharacters;
            totalSkippedCharacters += matcher.group().length();
            colors[foundMatches++] = colorForText(matcher.group().replaceAll("[<>]", ""));
        }
        text = text.replaceAll("<(.*?)>", "");
        int colorIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (foundMatches > 0) {
                if (colorIndexes[colorIndex] == i) {
                    g.setColor(colors[colorIndex++]);
                }
            }
            g.drawString("" + currentChar, currentX, y);
            currentX += WzFont.getStringWidth(g, "" + currentChar);
        }
    }

    /**
     * Method getExitBounds.
     *
     * @return Rectangle
     */
    @Override
    public Rectangle getExitBounds() {
        return new Rectangle((int) (getBounds().getWidth() + getBounds().getX() - 4 - 15),
                (int) getBounds().getY() + 4, 15, 15);
    }

    /**
     * Method render.
     *
     * @param g Graphics2D
     */
    @Override
    public void render(Graphics2D g) {
        drawChatboxInterface(g);
        super.render(g);
        drawChat(g);
    }

    /**
     * Method textForColor.
     *
     * @param c Color
     * @return String
     */
    private String textForColor(Color c) {
        return "0x" + componentToHex(c.getRed()) + componentToHex(c.getGreen())
                + componentToHex(c.getBlue());
    }

    /**
     * Constructor for ChatboxInterface.
     *
     * @param x          int
     * @param y          int
     * @param width      int
     * @param height     int
     * @param lineHeight int
     */
    public ChatboxInterface(int x, int y, int width, int height, int lineHeight) {
        super(x, y, width, height, lineHeight, true);
        InterfaceHandler.setChatbox(this);
        chatboxImage = ImageLoader.loadInterface("cb_bg");
        setInnerPanel(new Panel(2000, 15 * lineHeight));
    }

    private Color buddyTextColor = Color.green;
    private Image chatboxImage;
    private final byte GLOBAL_TEXT = 0, SHOUT_TEXT = 1, BUDDY_TEXT = 2, PLAYER_INPUT = 3,
            PLAYER_NAME = 4, WHISPER_TEXT = 5, OTHER_PLAYER_NAME = 6;
    private Color globalTextColor = Color.white;

    private Color otherPlayerNameColor = Color.orange;

    private Color playerInputColor = Color.white;

    private Color playerNameColor = Color.yellow;

    private Color shoutTextColor = Color.white;

    // Cyan, lightGray, MAYBE red
    private Color whisperTextColor = Color.pink;

}
