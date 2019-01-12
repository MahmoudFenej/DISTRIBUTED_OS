package com.mahmoud.model.entity;

import java.io.Serializable;

public class MessageTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String replyMessage;
	private String outputMessage;
	private String messageFormat;
	private String currentAction;

	public MessageTO() {
	}


	public MessageTO(String replyMessage, String outputMessage, String messageFormat, String currentAction) {
		super();
		this.replyMessage = replyMessage;
		this.outputMessage = outputMessage;
		this.messageFormat = messageFormat;
		this.currentAction = currentAction;
	}


	public String getOutputMessage() {
		return outputMessage;
	}

	public void setOutputMessage(String outputMessage) {
		this.outputMessage = outputMessage;
	}
	
	public String getReplyMessage() {
		return replyMessage;
	}
	
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	
	public String getMessageFormat() {
		return messageFormat;
	}
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}
	
	public String getCurrentAction() {
		return currentAction;
	}
	
	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}
	
	public boolean isNeedToReply() {
		return !replyMessage.isEmpty();
	}
	

}
