package com.sibb.net.packet;

import com.sibb.net.packet.impl.LoginResponsePacket;
import com.sibb.net.packet.impl.PlayerUpdatePacket;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class PacketHandler {
    private static PacketType packetHandlers[] = new PacketType[255];

    static {
        packetHandlers[0] = new LoginResponsePacket();
        packetHandlers[1] = new PlayerUpdatePacket();
    }

    /**
     * Method processPacket.
     *
     * @param p Packet
     */
    public static void processPacket(Packet p) {
        byte opcode = p.getOpcode();
        if (packetHandlers[opcode] != null) {
            packetHandlers[opcode].processPacket(p);
        } else {
            System.out.println("Unhandled packet -- Opcode: " + opcode + ", Length: "
                    + (p.getData().capacity() - 1));
        }
    }

}
