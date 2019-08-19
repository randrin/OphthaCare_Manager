package com.vane.ophthacare.exception;

public enum ExceptionCodes {

	ERROR_ADMIN_NO_PERMISSION("ERR0001","Admin sans permissions. Contactez votre administrateur"),
	ERROR_MEDECIN_NO_PERMISSION("ERR0002","Personnel sans permissions. Contactez votre administrateur");
	
	private String code;
	private String message;
	
	private ExceptionCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String toString() {
		return this.getCode() + "-> Message: " + this.getMessage();
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
