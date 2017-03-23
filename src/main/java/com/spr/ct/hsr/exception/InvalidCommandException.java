/**
 * 
 */
package com.spr.ct.hsr.exception;

/**
 * @author RNY
 *
 */
public class InvalidCommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3504910465775409068L;

	public InvalidCommandException() {
		super();
	}

	public InvalidCommandException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCommandException(String message) {
		super(message);
	}

	public InvalidCommandException(Throwable cause) {
		super(cause);
	}

}
