package com.china.ciic.bookgenerate.model.json;

public class BaseResponse {

	protected int status;
	protected String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseResponse() {

	}

}
