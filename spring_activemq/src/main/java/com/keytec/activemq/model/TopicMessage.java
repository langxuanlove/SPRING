package com.keytec.activemq.model;

import net.sf.oval.constraint.NotNull;

import com.rop.AbstractRopRequest;

public class TopicMessage extends AbstractRopRequest{
    //搞定
    @NotNull(profiles = { "altitude" })
	private String topic;
	private String message;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
