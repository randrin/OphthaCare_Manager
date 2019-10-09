package com.vane.ophthacare.exception;

public class Response {

	private String code;
	private String message;

	public Response(ResponseCodes errorCode) {
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	public Response(ExceptionCodes errorCode) {
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
