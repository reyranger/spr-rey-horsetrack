package com.spr.ct.hsr.service;

import java.util.List;

import com.spr.ct.hsr.bean.Horse;

/**
 * @author R.E.Y
 *
 */
public interface HorseRepoService {

	public static final String LOST = "lost";
	public static final String WON = "won";

	public int calculatePayout(int bet);

	public void markWinningHorse(int horseNumber);

	public List<String> getCurrentHorseStandings();
	
	public Horse getCurrentWinningHorse();
	
	public Horse getHorseDetails(int horseNumber);
	
	public void lineUpHorsesForRace();

}
