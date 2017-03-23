package com.spr.ct.hsr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.spr.ct.hsr.bean.Horse;
import com.spr.ct.hsr.db.HorseRepository;

/**
 * @author R.E.Y
 *
 */
public class HorseRepoServiceImpl implements HorseRepoService {
	final static Logger LOGGER = Logger.getLogger(BillInventoryServiceImpl.class);

	private HorseRepository horseRepository = HorseRepository.getInstance();
	private TreeSet<Horse> horseStandings = horseRepository.getCurrentHorseStandings();
	private List<String> horseStandingsList = new ArrayList<String>();

	@Override
	public int calculatePayout(int bet) {
		return horseStandings.stream().filter(e -> e.hasWon()).findFirst().get().getOdds() * bet;
	}

	@Override
	public void markWinningHorse(int horseNumber) {
		// set won status to false first
		horseStandings.stream().forEach(e -> e.setWon(false));
		
		// set winning horse
		horseStandings.stream().filter(e -> e.getHorseId() == horseNumber).findFirst().get().setWon(true);
	}
	
	@Override
	public Horse getHorseDetails(int horseNumber) {
		// return horse
		return horseStandings.stream().filter(e -> e.getHorseId() == horseNumber).findFirst().get();
	}

	@Override
	public List<String> getCurrentHorseStandings() {
		horseStandingsList = horseStandings.stream().sorted((b1, b2) -> b1.getHorseId() - b2.getHorseId())
				.map(e -> e.getHorseId() + "," + e.getHorseName() + "," + e.getOdds() + "," + (e.hasWon() ? WON : LOST))
				.collect(Collectors.toList());
		return horseStandingsList;
	}

	@Override
	public Horse getCurrentWinningHorse() {
		return horseStandings.stream().filter(e -> e.hasWon()).findFirst().get();
	}
	
	@Override
	public void lineUpHorsesForRace() {
		horseStandings.stream().forEach(e -> e.setWon(false));
		horseStandings.stream().filter(e -> e.getHorseId() == 1).findFirst().get().setWon(true);		
	}

}
