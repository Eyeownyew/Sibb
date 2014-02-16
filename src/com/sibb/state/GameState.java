package com.sibb.state;

import com.sibb.*;
import com.sibb.Window;
import com.sibb.input.Keyboard;
import com.sibb.util.ImageLoader;
import com.sibb.util.WzFont;
import com.sibb.visual.impl.ChatboxInterface;
import com.sibb.visual.impl.InventoryInterface;
import com.sibb.world.Chat;
import com.sibb.world.MapLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class GameState extends State {

    private static GameState instance = null;

    /**
     * Method getInstance.
     *
     * @return GameState
     */
    public static GameState getInstance() {
        return instance;
    }

    /**
     * Method drawString.
     *
     * @param g Graphics2D
     * @param s String
     * @param x int
     * @param y int
     */
    public void drawString(Graphics2D g, String s, int x, int y) {
        g.drawString(s, x, y);
    }

    /**
     * Method drawStringCentered.
     *
     * @param g Graphics2D
     * @param s String
     * @param x int
     * @param y int
     */
    public void drawStringCentered(Graphics2D g, String s, int x, int y) {
        drawString(g, s, x - (g.getFontMetrics().stringWidth(s) / 2), y);
    }

    /**
     * Method getID.
     *
     * @return String
     */
    @Override
    public String getID() {
        return "Game";
    }

    /**
     * Method init.
     *
     * @param gc GameContainer
     */
    @Override
    public void init(GameContainer gc) {
        instance = this;
        g2 = gc.getGraphics();
        font = Engine.getInstance().getFont();
        map = MapLoader.getInstance();
        map.loadMap("data/maps/betamap.tmx");
        character = ImageLoader.loadImage("character");
        initInterfaces();
        init = true;
    }

    private void initInterfaces() {
        new ChatboxInterface(0, Window.getFrame().getHeight() - 240, 600, 240, (int) WzFont.getDefaultFontSize() + 2);
        new InventoryInterface(600, 240);
    }

    public boolean chatSelected = false;

    public void handleKeys() {
        for (Object o : Keyboard.getKeysPressed()) {
            Entry<Integer, Character> entry = (Entry<Integer, Character>) o;
            int key = entry.getKey();
            char c = entry.getValue();
            switch (key) {
                case KeyEvent.VK_EQUALS:
                    System.exit(0);
                    break;
                case KeyEvent.VK_UP:
                    Client.getClient().movePlayer(0, -moveDistance);
                    break;
                case KeyEvent.VK_DOWN:
                    Client.getClient().movePlayer(0, moveDistance);
                    break;
                case KeyEvent.VK_LEFT:
                    Client.getClient().movePlayer(-moveDistance, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    Client.getClient().movePlayer(moveDistance, 0);
                    break;
                case KeyEvent.VK_SPACE:
                    // jump
                    break;
                case KeyEvent.VK_ESCAPE:
                    chatSelected = false;
                    playerEnteredText = "";
                    break;
                case KeyEvent.VK_F8:
                    displayMinimap = !displayMinimap;
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if (playerEnteredText.length() > 0)
                        playerEnteredText = playerEnteredText.substring(0, playerEnteredText.length() - 1);
                    else
                        chatSelected = false;
                    break;
                case KeyEvent.VK_ENTER:
                    if (chatSelected) {
                        if (playerEnteredText != "") {
                            Chat.messageEntered(playerEnteredText);
                            playerEnteredText = "";
                        }
                    } else {
                        chatSelected = true;
                    }
                    break;
                case KeyEvent.VK_CONTROL:
                    Window.getCamera().setFollowing(Client.getClient().getPlayer());
                    break;
                default:
                    if (chatSelected) {
                        for (char j : Client.validChars) {
                            if (j != c)
                                continue;
                            if (WzFont.getStringWidth(g2, playerEnteredText + c) + 20 < 587 - WzFont.getStringWidth(g2, Client.getClient().getUsername()
                                    + ":")) {
                                playerEnteredText += c;
                            }
                        }
                        break;
                    }
                    System.out.println("GameState: Unhandled key event - " + key);
                    break;
            }
            if (!repeatableKeys.contains(key))
                Keyboard.remove(key);
        }
    }

    private int moveDistance = 16;

    /**
     * Method mouseClicked.
     *
     * @param button     int
     * @param x          int
     * @param y          int
     * @param clickCount int
     */
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {

    }

    /**
     * Method render.
     *
     * @param gc GameContainer
     * @param g  Graphics2D
     */
    @Override
    public void render(GameContainer gc, Graphics2D g) {
        Window.getCamera().update();

        map.drawMap(g);
        g.setColor(Color.white);
        g.drawString("pX: " + Client.getClient().getPlayer().getX() + ", pY: " + Client.getClient().getPlayer().getY(), 20, 200);
        if (displayMinimap) {

            g.setColor(new Color(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue(), .9f));
            g.fillRect(0, 0, Main.app.getWidth(), Main.app.getHeight());

            map.drawMap(g);

            g.setColor(Color.white);
            drawStringCentered(g, "Minimap", gc.getWidth() / 2, 20);
        }
        g.drawImage(character, Client.getClient().getPlayer().getX() - Window.getCamera().getX(), Client.getClient().getPlayer().getY() - Window.getCamera().getY(), null);

    }

    private Image character;

    /**
     * Method update.
     *
     * @param gc    GameContainer
     * @param delta int
     */
    @Override
    public void update(GameContainer gc, int delta) {
        handleKeys();
    }

    private boolean displayMinimap;

    Font font = null;

    Graphics2D g2 = null;

    MapLoader map = null;

    public String playerEnteredText = "";
    @SuppressWarnings("serial")
    final List<Integer> repeatableKeys = new ArrayList<Integer>() {
        {
            add(KeyEvent.VK_LEFT);
            add(KeyEvent.VK_RIGHT);
            add(KeyEvent.VK_UP);
            add(KeyEvent.VK_DOWN);
            add(KeyEvent.VK_SPACE);
        }
    };

}
