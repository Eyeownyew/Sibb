package com.sibb.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.sibb.Client;

public class ClientHandler extends SimpleChannelHandler {
	protected Client c = null;

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		c = new Client(ctx.getChannel());
		System.out.println("Bound");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		ChannelBuffer packet = (ChannelBuffer)e.getMessage();
		System.out.println((packet.readByte() == 1 ? "Success!" : "Could not authenticate"));
	}

}
