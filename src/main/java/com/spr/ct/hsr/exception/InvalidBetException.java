package com.spr.ct.hsr.exception;

/**
 * @author RNY
 *
 */
public class InvalidBetException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1079769059333119649L;

	public InvalidBetException() {
		super();
	}

	public InvalidBetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidBetException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidBetException(String message) {
		super(message);
	}

	public InvalidBetException(Throwable cause) {
		super(cause);
	}


}
