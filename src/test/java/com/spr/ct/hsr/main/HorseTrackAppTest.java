package com.spr.ct.hsr.main;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class HorseTrackAppTest extends TestCase {
	@Test
	public void testHorseTrackApp() {
		HorseTrackApp.runApp("1 55");
		System.out.println("\n------\n");
		
		HorseTrackApp.runApp("2 25");
		System.out.println("\n------\n");
		
		HorseTrackApp.runApp("w 4");
		System.out.println("\n------\n");
		
		HorseTrackApp.runApp("4 10.25");
		System.out.println("\n------\n");
	}
}
