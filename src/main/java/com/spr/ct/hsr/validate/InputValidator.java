package com.spr.ct.hsr.validate;

import org.apache.log4j.Logger;

import com.spr.ct.hsr.exception.InvalidBetException;
import com.spr.ct.hsr.exception.InvalidCommandException;

/**
 * @author R.E.Y
 *
 */
public class InputValidator {

	final static Logger LOGGER = Logger.getLogger(InputValidator.class);
	private static String regex_chars_1 = "[rqRQ]";
	private static String regex_chars_2 = "[wW]";
	private static String regex_chars_3 = "[1-7]";

	private static String command = null;
	private String cmdMessage;
	private String betMessage;

	public boolean validate(String inputCommand) throws InvalidCommandException,InvalidBetException {
		if (null != inputCommand) {
			command = inputCommand.trim();
			cmdMessage = "Invalid Command: " + inputCommand;
			betMessage = "Invalid Bet: ";
			String[] cmdParts = command.split(" ");
			if (cmdParts.length > 0 && cmdParts.length <= 2) {
				if (cmdParts[0].matches(regex_chars_1)) {
					LOGGER.info("Input Command Validated ## " + inputCommand);
					if(cmdParts.length == 1) {
						return true;
					} else {
						throw new InvalidCommandException(cmdMessage);
					}
					
				} else if (cmdParts[0].matches(regex_chars_2)) {
					if (cmdParts[1].matches(regex_chars_3)) {
						LOGGER.info("Input Command Validated ## " + inputCommand);
						return true;
					} else {
						throw new InvalidCommandException(cmdMessage);
					}
				} else if (cmdParts[0].matches(regex_chars_3)) {
					if (Float.parseFloat(cmdParts[1]) % 1 == 0) {
						LOGGER.info("Input Command Validated ## " + inputCommand);
						return true;
					} else {
						throw new InvalidBetException(betMessage + cmdParts[1]);
					}
				} else {
					throw new InvalidCommandException(cmdMessage);
				}
			} else {
				throw new InvalidCommandException(cmdMessage);
			}
		} else {
			throw new InvalidCommandException(cmdMessage);
		}
	}
}
