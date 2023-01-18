/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution.asn2;

import java.io.Serializable;

public abstract class UserAccount implements Serializable {
	private static final long serialVersionUID = -7687530926191311692L;
	private String username;
	private String password;

	/**
	 * Sets the type of this user.
	 * 
	 * @param type the type.
	 */
	public abstract void setType(String type);

	/**
	 * Creates an instance of this class.
	 * 
	 * @param username sets the user name.
	 * @param password sets the password.
	 */
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Returns the user name.
	 * 
	 * @return the user name.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns true if the supplied password matches the password on this class.
	 * 
	 * @param password the password to evaluate.
	 * @return true if correct.
	 */
	public boolean isPasswordCorrect(String password) {
		return password == null ? false : this.password.equals(password);
	}

	@Override
	public String toString() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
