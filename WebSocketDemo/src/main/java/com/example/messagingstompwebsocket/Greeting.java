package com.example.messagingstompwebsocket;

public class Greeting {

	private String content;
	private long startTime;

	public Greeting() {
	}

	public Greeting(String content, long startTime) {
		this.content = content;
		this.startTime = startTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
