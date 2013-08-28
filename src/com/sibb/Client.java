package com.sibb;

import org.jboss.netty.channel.Channel;

import com.sibb.net.Constructor;

public class Client extends Player {

	static Channel channel = null;
	static Client client = null;
	public static Client getClient() {
		return client;
	}

	public static Player getPlayer() {
		return (Player) client;
	}

	public Client(Channel channel) {
		super(channel);
		super.channel = channel;
		client = this;
		constructor = new Constructor(this);
	}

}
