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
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		Client c = Client.getClient();
		String hex = WzSecurity.md5(c.getPassword() + c.getUsername());
		buffer.writeByte(0);
		buffer.writeByte(c.getUsername().length());
		buffer.writeBytes(c.getUsername().getBytes());
		buffer.writeByte(hex.length());
		buffer.writeBytes(hex.getBytes());
		return buffer;
	}

	public ChannelBuffer messagePacket(String message) {
		ChannelBuffer data = ChannelBuffers.dynamicBuffer();
		data.writeByte(1);
		data.writeInt(message.length());
		data.writeBytes(message.getBytes());
		return data;
	}
}
