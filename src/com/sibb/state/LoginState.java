package com.sibb.state;

import com.sibb.*;
import com.sibb.Window;
import com.sibb.input.Keyboard;
import com.sibb.util.WzFont;
import org.jboss.netty.channel.Channel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.util.Map.Entry;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class LoginState extends State {

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
    }

    /**
     * Method getID.
     *
     * @return String
     */
    @Override
    public String getID() {
        return "login";
    }

    /**
     * Method init.
     *
     * @param gc GameContainer
     */
    @Override
    public void init(GameContainer gc) {
        font = Engine.getInstance().getFont();
        g2 = Window.getContainer().getGraphics();
        init = true;
    }

    public void handleKeys() {
        for (Object o : Keyboard.getKeysPressed()) {
            Entry<Integer, Character> entry = (Entry<Integer, Character>) o;
            int key = entry.getKey();
            char c = entry.getValue();
            switch (key) {
                case KeyEvent.VK_ESCAPE:
                    Main.app.requestExit();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if (selectedBox == 0 && username.length() > 0)
                        username = username.substring(0, username.length() - 1);
                    else if (selectedBox == 1 && password.length() > 0)
                        password = password.substring(0, password.length() - 1);
                    break;
                case KeyEvent.VK_TAB:
                    if (selectedBox == 0)
                        selectedBox = 1;
                    else
                        selectedBox = 0;
                    break;
                case KeyEvent.VK_ENTER:
                    if (selectedBox == 0) {
                        selectedBox = 1;
                    } else {
                        attemptLogin();
                    }
                    break;
                default:
                    for (char j : Client.validChars) {
                        if (j != c)
                            continue;
                        if (selectedBox == 0 && username.length() < 12)
                            username += c;
                        else if (selectedBox == 1 && password.length() < 16)
                            password += c;
                    }
                    break;
            }
            Keyboard.remove(key);
        }
    }

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
        GameContainer container = Window.getContainer();
        if (x > (container.getWidth() / 2) - ((WzFont.getStringWidth(g2, "Login")) / 2) - 200
                && x < (container.getWidth() / 2) - ((WzFont.getStringWidth(g2, "Login")) / 2) + 200 && y < (container.getHeight() / 2) + 120
                && y > (container.getHeight() / 2) + 100) {
            attemptLogin();
        }
    }

    /**
     * Method render.
     *
     * @param gc GameContainer
     * @param g  Graphics2D
     */
    @Override
    public void render(GameContainer gc, Graphics2D g) {
        int width = 400;
        int height = 20;
        g.setColor(Color.darkGray);
        g.fill(new Rectangle((gc.getWidth() / 2) - (width / 2), (gc.getHeight() / 2) - 100, width, height));
        g.fill(new Rectangle((gc.getWidth() / 2) - (width / 2), (gc.getHeight() / 2), width, height));
        g.fill(new Rectangle((gc.getWidth() / 2) - (width / 2), (gc.getHeight() / 2) + 100, width, height));
        g.setColor(Color.yellow);
        g.drawString(username, (gc.getWidth() / 2) - (WzFont.getStringWidth(g, username) / 2), (gc.getHeight() / 2) - 86);
        String passwordBlur = "";
        for (int i = 0; i < password.length(); i++)
            passwordBlur += "*";
        g.setColor(Color.white);
        g.drawString(passwordBlur, (gc.getWidth() / 2) - (WzFont.getStringWidth(g, passwordBlur) / 2), (gc.getHeight() / 2) + 14);
        g.setColor(Color.blue);
        g.drawString("Login", (gc.getWidth() / 2) - (WzFont.getStringWidth(g, "Login") / 2), (gc.getHeight() / 2) + 114);
        g.setColor(Color.red);
        g.drawString(errorMessage, (gc.getWidth() / 2) - (WzFont.getStringWidth(g, errorMessage) / 2), (gc.getHeight() / 2) - 200);
        g.drawString(errorMessage2, (gc.getWidth() / 2) - (WzFont.getStringWidth(g, errorMessage2) / 2), (gc.getHeight() / 2) - 180);
        g.drawString("" + selectedBox, 10, 10);
    }

    /**
     * Method update.
     *
     * @param gc    GameContainer
     * @param delta int
     */
    @Override
    public void update(GameContainer gc, int delta) {
        if (g2 == null)
            g2 = Window.getContainer().getGraphics();
        handleKeys();
    }

    String errorMessage = "";

    String errorMessage2 = "";

    Font font;

    /**

     */

    Graphics2D g2 = null;

    boolean messageSent = false;

    DataOutputStream out = null;

    String password = "";

    int selectedBox = 0;

    String username = "";

}
