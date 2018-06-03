package com.mtt.guildhome.guildpost.domain;

import java.util.Date;

public class ErrorInfo {
	private Date timestamp;
	private String message;
	private String details;

	private ErrorInfo() {
		super();
	}

	public ErrorInfo(String message, String details) {
		this(new Date(), message, details);
	}

	public ErrorInfo(Date timestamp, String message, String details) {
		this();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
