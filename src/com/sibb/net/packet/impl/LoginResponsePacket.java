package com.sibb.net.packet.impl;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sibb.Engine;
import com.sibb.net.packet.Packet;
import com.sibb.net.packet.PacketType;

public class LoginResponsePacket implements PacketType {

	@Override
	public void processPacket(Packet p) {
		ChannelBuffer data = p.getData();
		boolean successfulLogin = data.readByte() == 1;
		if(successfulLogin) {
			Engine.getInstance().loggedIn();
			System.out.println("Successful login.");
		} else
			System.out.println("Unsuccessful login.");
	}

}
