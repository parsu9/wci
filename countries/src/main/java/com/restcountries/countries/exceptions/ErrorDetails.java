package com.restcountries.countries.exceptions;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String messgage;
	private String details;

	public ErrorDetails(Date timestamp, String messgage, String details) {
		super();
		this.timestamp = timestamp;
		this.messgage = messgage;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessgage() {
		return messgage;
	}

	public void setMessgage(String messgage) {
		this.messgage = messgage;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ErrorDetailsBO [timestamp=" + timestamp + ", messgage=" + messgage + ", details=" + details + "]";
	}

}
