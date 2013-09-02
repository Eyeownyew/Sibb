package com.sibb.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sibb.net.packet.Packet;

public class PacketDecoder {

	private static byte opcode = 0;

	public static Packet decode(ChannelBuffer data) {
			opcode = data.readByte();
			return new Packet(opcode, data);
	}

}
