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

public class LinkedInException extends Exception {
	private static final long serialVersionUID = -4354894027723180456L;

	public LinkedInException() {
		super();
	}

	public LinkedInException(String message) {
		super(message);
	}

	public LinkedInException(String message, Throwable cause) {
		super(message, cause);
	}

	public LinkedInException(Throwable cause) {
		super(cause);
	}

	protected LinkedInException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
