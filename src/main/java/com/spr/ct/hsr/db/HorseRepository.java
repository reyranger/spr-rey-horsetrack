package com.spr.ct.hsr.db;

import java.util.TreeSet;

import com.spr.ct.hsr.bean.Horse;

/**
 * @author R.E.Y
 * 
 *         Bill Inventory/Repository to access DB DB is hard initialized with a
 *         TreeSet. TreeSet key holds the Horse object
 *
 */
public class HorseRepository {

	/**
	 * static object for singleton instance
	 */
	private static HorseRepository horseRepo = null;

	/**
	 * private horses database to hold horses details
	 */
	private static TreeSet<Horse> horseDB = null;
	
	/**
	 * private constructor for singleton  
	 */
	private HorseRepository() {
		// add a comparator to sort horse details by id
		horseDB = new TreeSet<Horse>((Horse o1, Horse o2) -> o1.getHorseId() - o2.getHorseId());
		initHorseLineUp();
	}

	/**
	 * initialize db with horses details
	 */
	private void initHorseLineUp() {
		if (null != horseDB) {
			// insert horses details
			horseDB.add(new Horse() {
				{
					setHorseId(1);
					setHorseName("That Darn Gray Cat");
					setOdds(5);
					setWon(true);
				}
			});
			horseDB.add(new Horse() {
				{
					setHorseId(2);
					setHorseName("Fort Utopia");
					setOdds(10);
				}
			});
			horseDB.add(new Horse() {
				{
					setHorseId(3);
					setHorseName("Count Sheep");
					setOdds(9);
				}
			});
			horseDB.add(new Horse() {
				{
					setHorseId(4);
					setHorseName("Ms Traitour");
					setOdds(4);
				}
			});
			horseDB.add(new Horse() {
				{
					setHorseId(5);
					setHorseName("Real Princess");
					setOdds(3);
				}
			});
			horseDB.add(new Horse() {
				{
					setHorseId(6);
					setHorseName("Pa Kettle");
					setOdds(5);
				}
			});
			horseDB.add(new Horse() {
				{
					setHorseId(7);
					setHorseName("Gin Stinger");
					setOdds(6);
				}
			});
		}

	}

	/**
	 * return singleton object for horses repository ps note this is not
	 * synchronized
	 * 
	 * @return HorseRepository
	 */
	public static HorseRepository getInstance() {
		if (horseRepo == null) {
			horseRepo = new HorseRepository();
		}
		return horseRepo;
	}
	
	/**
	 * return horseStandings
	 * @return billsDB Map
	 */
	public TreeSet<Horse> getCurrentHorseStandings() {
		return horseDB;
	}
}
