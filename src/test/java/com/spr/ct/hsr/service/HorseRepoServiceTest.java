package com.spr.ct.hsr.service;

import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author R.E.Y
 *
 */
public class HorseRepoServiceTest extends TestCase {

	final static Logger LOGGER = Logger.getLogger(BillInventoryServiceTest.class);
	private HorseRepoService horseRepoService = new HorseRepoServiceImpl();

	@Test
	public void testCalculatePayout() {
		
		horseRepoService.markWinningHorse(1);
		assertEquals(horseRepoService.calculatePayout(200), 1000);
		horseRepoService.markWinningHorse(2);
		assertEquals(horseRepoService.calculatePayout(200), 2000);
		horseRepoService.markWinningHorse(3);
		assertEquals(horseRepoService.calculatePayout(200), 1800);
		horseRepoService.markWinningHorse(4);
		assertEquals(horseRepoService.calculatePayout(200), 800);
		horseRepoService.markWinningHorse(5);
		assertEquals(horseRepoService.calculatePayout(200), 600);
		horseRepoService.markWinningHorse(6);
		assertEquals(horseRepoService.calculatePayout(200), 1000);
		horseRepoService.markWinningHorse(7);
		assertEquals(horseRepoService.calculatePayout(200), 1200);

		horseRepoService.markWinningHorse(1);
		assertFalse(horseRepoService.calculatePayout(100) == 1000);
		horseRepoService.markWinningHorse(2);
		assertFalse(horseRepoService.calculatePayout(100) == 2000);
		horseRepoService.markWinningHorse(3);
		assertFalse(horseRepoService.calculatePayout(100) == 1800);
		horseRepoService.markWinningHorse(4);
		assertFalse(horseRepoService.calculatePayout(100) == 800);
		horseRepoService.markWinningHorse(5);
		assertFalse(horseRepoService.calculatePayout(100) == 600);
		horseRepoService.markWinningHorse(6);
		assertFalse(horseRepoService.calculatePayout(100) == 1000);
		horseRepoService.markWinningHorse(7);
		assertFalse(horseRepoService.calculatePayout(100) == 1200);

	}
	
	@Test
	public void testGetCurrentHorseStandings() {
		horseRepoService.lineUpHorsesForRace();
		horseRepoService.getCurrentHorseStandings().stream().forEach(System.out::println);
		System.out.println("\n------\n");
		
		horseRepoService.markWinningHorse(4);
		horseRepoService.getCurrentHorseStandings().stream().forEach(System.out::println);
		System.out.println("\n------\n");
		
		horseRepoService.markWinningHorse(3);
		horseRepoService.getCurrentHorseStandings().stream().forEach(System.out::println);
		System.out.println("\n------\n");
		
		horseRepoService.markWinningHorse(1);
		horseRepoService.getCurrentHorseStandings().stream().forEach(System.out::println);
		System.out.println("\n------\n");
	}
	

	@Test
	public void testMarkWinningHorse() {

		horseRepoService.lineUpHorsesForRace();
		assertEquals(horseRepoService.getCurrentWinningHorse().getHorseId(), 1);

		horseRepoService.markWinningHorse(2);
		assertEquals(horseRepoService.getCurrentWinningHorse().getHorseId(), 2);

		horseRepoService.markWinningHorse(4);
		assertEquals(horseRepoService.getCurrentWinningHorse().getHorseId(), 4);

		horseRepoService.markWinningHorse(7);
		assertEquals(horseRepoService.getCurrentWinningHorse().getHorseId(), 7);
		

		horseRepoService.markWinningHorse(2);
		assertFalse(horseRepoService.getCurrentWinningHorse().getHorseId() == 3);

		horseRepoService.markWinningHorse(4);
		assertFalse(horseRepoService.getCurrentWinningHorse().getHorseId() == 8);

		horseRepoService.markWinningHorse(7);
		assertFalse(horseRepoService.getCurrentWinningHorse().getHorseId() == 8);

	}

}
