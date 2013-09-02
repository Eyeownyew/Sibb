package com.sibb.world;

import java.util.ArrayList;
import java.util.List;

import com.sibb.Client;
import com.sibb.visual.InterfaceHandler;
import com.sibb.visual.impl.ChatboxInterface;

public class Chat {

	private static List<Message> cbText = new ArrayList<Message>();

	public static List<Message> getCbText() {
		return cbText;
	}
	
	public static void messageEntered(String playerEnteredText) {
		Client.getClient().getChannel()
				.write(Client.getClient().getConstructor().messagePacket(playerEnteredText));
	}
	public static void messageReceived(Message message) {
		cbText.add(message);
		ChatboxInterface chatbox = InterfaceHandler.getChatbox();
		if (cbText.size() > 100) {
			cbText.remove(0);
			chatbox.setScrollY(chatbox.getScrollY()+1);
		}
		if (chatbox.getScrollY() != 0 && chatbox.getScrollY() > -100) {
			chatbox.setScrollY(chatbox.getScrollY() - 1);
		}
	}
}
