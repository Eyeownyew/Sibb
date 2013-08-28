package com.sibb;

import org.jboss.netty.channel.Channel;

import com.sibb.net.Constructor;

public class Player {

	protected Channel channel = null;
	protected Constructor constructor = null;

	String password = "";

	String username = "";

	int x = 0;

	int y = 0;

	public Player(Channel channel) {

	}

	public Channel getChannel() {
		return channel;
	}

	public Constructor getConstructor() {
		return constructor;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
