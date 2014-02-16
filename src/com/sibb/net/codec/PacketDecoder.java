package com.sibb.net.codec;

import com.sibb.net.packet.Packet;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class PacketDecoder {

    private static byte opcode = 0;

    /**
     * Method decode.
     *
     * @param data ChannelBuffer
     * @return Packet
     */
    public static Packet decode(ChannelBuffer data) {
        opcode = data.readByte();
        return new Packet(opcode, data);
    }

}
