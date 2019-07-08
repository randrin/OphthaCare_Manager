package com.vane.ophthacare.model;

import java.io.Serializable;

public class Session implements Serializable {

	private static final long serialVersionUID = 6438628423017391642L;

	private boolean check;

	private String role;

	public Session() {}

	
	public Session(boolean check, String role) {
		this.check = check;
		this.role = role;
	}

	/**
	 * @return the check
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
