package com.spr.ct.hsr.exception;

/**
 * @author R.E.Y
 *
 */
public class InvalidHorseNumberException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2279063794754271600L;

	public InvalidHorseNumberException() {
		super();
	}

	public InvalidHorseNumberException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidHorseNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidHorseNumberException(String message) {
		super(message);		
	}

	public InvalidHorseNumberException(Throwable cause) {
		super(cause);
	}

}
