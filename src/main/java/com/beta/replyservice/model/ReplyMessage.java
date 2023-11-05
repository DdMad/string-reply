package com.beta.replyservice.model;

public class ReplyMessage implements Message {

	private final String data;

	public ReplyMessage(String data) {
		this.data = data;
	}

	@Override
	public String getData() {
		return data;
	}
}