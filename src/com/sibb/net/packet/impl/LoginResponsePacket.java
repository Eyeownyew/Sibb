package com.sibb.net.packet.impl;

import com.sibb.Engine;
import com.sibb.net.packet.Packet;
import com.sibb.net.packet.PacketType;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class LoginResponsePacket implements PacketType {

    /**
     * Method processPacket.
     *
     * @param p Packet
     * @see com.sibb.net.packet.PacketType#processPacket(Packet)
     */
    @Override
    public void processPacket(Packet p) {
        ChannelBuffer data = p.getData();
        boolean successfulLogin = data.readByte() == 1;
        if (successfulLogin) {
            Engine.getInstance().loggedIn();
            System.out.println("Successful login.");
        } else
            System.out.println("Unsuccessful login.");
    }

}
