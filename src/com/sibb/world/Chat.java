package com.sibb.world;

import com.sibb.Client;
import com.sibb.net.packet.type.Message;
import com.sibb.visual.InterfaceHandler;
import com.sibb.visual.impl.ChatboxInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Chat {

    private static List<Message> cbText = new ArrayList<Message>();

    /**
     * Method getCbText.
     *
     * @return List<Message>
     */
    public static List<Message> getCbText() {
        return cbText;
    }

    /**
     * Method messageEntered.
     *
     * @param playerEnteredText String
     */
    public static void messageEntered(String playerEnteredText) {
        Client.getClient().getChannel()
                .write(Client.getClient().getConstructor().messagePacket(playerEnteredText));
    }

    /**
     * Method messageReceived.
     *
     * @param message Message
     */
    public static void messageReceived(Message message) {
        cbText.add(message);
        ChatboxInterface chatbox = InterfaceHandler.getChatbox();
        if (cbText.size() > 100) {
            cbText.remove(0);
            chatbox.setScrollY(chatbox.getScrollY() + 1);
        }
        if (chatbox.getScrollY() != 0 && chatbox.getScrollY() > -100) {
            chatbox.setScrollY(chatbox.getScrollY() - 1);
        }
    }
}
