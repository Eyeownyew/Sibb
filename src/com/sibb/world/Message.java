package com.sibb.world;

public class Message {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	private int type;

	public int getType() {
		return type;
	}

	public Message(String username, String message, int type) {
		this.username = username;
		this.message = message;
		this.type = type;
	}
}
