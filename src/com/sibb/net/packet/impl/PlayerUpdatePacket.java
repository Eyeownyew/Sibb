package com.sibb.net.packet.impl;

import com.sibb.net.packet.Packet;
import com.sibb.net.packet.PacketType;
import com.sibb.net.packet.type.Message;
import com.sibb.world.Chat;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class PlayerUpdatePacket implements PacketType {

    /**
     * Method processPacket.
     *
     * @param p Packet
     * @see com.sibb.net.packet.PacketType#processPacket(Packet)
     */
    @Override
    public void processPacket(Packet p) {
        String username = "";
        String message = "";
        ChannelBuffer data = p.getData();
        int updateFlags = data.readByte();
        if ((updateFlags & 0x1) == 0x1) {
            int messagesToRead = p.getData().readByte();
            for (int i = 0; i < messagesToRead; i++) {
                byte usernameLength = data.readByte();
                for (int j = 0; j < usernameLength; j++)
                    username += (char) data.readByte();
                int messageLength = data.readInt();
                for (int j = 0; j < messageLength; j++)
                    message += (char) data.readByte();
                int type = data.readByte();
                Chat.messageReceived(new Message(username, message, type));
            }
        }
    }

}
