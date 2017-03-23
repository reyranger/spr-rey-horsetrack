package com.spr.ct.hsr.validate;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.spr.ct.hsr.exception.InvalidBetException;
import com.spr.ct.hsr.exception.InvalidCommandException;
import com.spr.ct.hsr.service.BillInventoryServiceTest;

import junit.framework.TestCase;

public class InputValidatorTest extends TestCase {
	final static Logger LOGGER = Logger.getLogger(BillInventoryServiceTest.class);
	InputValidator inp = new InputValidator();
	@Test
	public void testInputValidator() {
		try {
			assertEquals(inp.validate("r ") , true);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			inp.validate("r 1");
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(inp.validate("R ") , true);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(inp.validate("q ") , true);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(inp.validate("Q ") , true);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(inp.validate("w 1 ") , true);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			inp.validate("w 8 "); 
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			inp.validate("1 1.5 ");
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(inp.validate("2 91"), true);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(inp.validate("3 100 "), true);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
