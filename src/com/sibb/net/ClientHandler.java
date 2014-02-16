package com.sibb.net;

import com.sibb.Client;
import com.sibb.net.codec.PacketDecoder;
import com.sibb.net.packet.Packet;
import com.sibb.net.packet.PacketHandler;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class ClientHandler extends SimpleChannelHandler {
    /**
     * Method channelOpen.
     *
     * @param ctx ChannelHandlerContext
     * @param e   ChannelStateEvent
     * @throws Exception
     */
    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        c = new Client(ctx.getChannel());
        System.out.println("Bound");
    }

    /**
     * Method exceptionCaught.
     *
     * @param ctx ChannelHandlerContext
     * @param e   ExceptionEvent
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }

    /**
     * Method messageReceived.
     *
     * @param ctx ChannelHandlerContext
     * @param e   MessageEvent
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Packet p = PacketDecoder.decode((ChannelBuffer) e.getMessage());
        PacketHandler.processPacket(p);
    }

    protected Client c = null;

}
