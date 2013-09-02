package com.sibb.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet {

	private byte opcode = 0;
	private ChannelBuffer data = null;

	public Packet(byte opcode, ChannelBuffer data) {
		setOpcode(opcode);
		setData(data);
	}

	public byte getOpcode() {
		return opcode;
	}

	public void setOpcode(byte opcode) {
		this.opcode = opcode;
	}

	public ChannelBuffer getData() {
		return data;
	}

	public void setData(ChannelBuffer data) {
		this.data = data;
	}
}
