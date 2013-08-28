package com.sibb.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.sibb.Client;
import com.sibb.util.WzSecurity;

public class Constructor {
	Client c = null;

	public Constructor(Client c) {
		this.c = c;
	}

	public ChannelBuffer loginPacket() {
		ChannelBuffer buffer = ChannelBuffers.buffer(200);
		Client c = Client.getClient();
		String hex = WzSecurity.md5(c.getPassword() + c.getUsername());

		try {
			buffer.writeByte(1);
			buffer.writeByte(c.getUsername().length());
			buffer.writeBytes(c.getUsername().getBytes());
			buffer.writeByte(hex.length());
			buffer.writeBytes(hex.getBytes());
			System.out.println("" + hex.length() + " " + hex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;

	}
}
