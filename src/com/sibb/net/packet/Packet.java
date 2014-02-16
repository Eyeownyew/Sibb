package com.sibb.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Packet {

    /**
     * Method getData.
     *
     * @return ChannelBuffer
     */
    public ChannelBuffer getData() {
        return data;
    }

    /**
     * Method getOpcode.
     *
     * @return byte
     */
    public byte getOpcode() {
        return opcode;
    }

    /**
     * Method setData.
     *
     * @param data ChannelBuffer
     */
    public void setData(ChannelBuffer data) {
        this.data = data;
    }

    /**
     * Method setOpcode.
     *
     * @param opcode byte
     */
    public void setOpcode(byte opcode) {
        this.opcode = opcode;
    }

    /**
     * Constructor for Packet.
     *
     * @param opcode byte
     * @param data   ChannelBuffer
     */
    public Packet(byte opcode, ChannelBuffer data) {
        setOpcode(opcode);
        setData(data);
    }

    private ChannelBuffer data = null;

    private byte opcode = 0;
}
