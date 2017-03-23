package com.spr.ct.hsr.db;

import java.util.Map;
import java.util.TreeMap;

import com.spr.ct.hsr.bean.Bill;

/**
 * @author R.E.Y
 * 
 *         Bill Inventory/Repository to access DB DB is hard initialized with a
 *         TreeMap. TreeMap key holds the Bill object TreeMap value holds
 *         inventory count for easy updates/dispensing cash reStock method will
 *         add default cash limits for all bills
 *
 */
public class BillInventory {

	/**
	 * static object for singleton instance
	 */
	private static BillInventory inventoryRepo = null;

	/**
	 * private bills database to stock cash in inventory
	 */
	private static TreeMap<Bill, Integer> billsDB = null;

	/**
	 * private constructor for singleton
	 */
	private BillInventory() {
		// add a comparator to sort bills by denomination
		billsDB = new TreeMap<Bill, Integer>((Bill o1, Bill o2) -> o1.getDenomination() - o2.getDenomination());
		initInventory();
	}

	/**
	 * initialize db with default inventory
	 */
	private void initInventory() {
		if (null != billsDB) {
			billsDB.put(new Bill() {
				{
					setDenomination(1);
					setBillName("$1");
				}
			}, 10);
			billsDB.put(new Bill() {
				{
					setDenomination(5);
					setBillName("$5");
				}
			}, 10);
			billsDB.put(new Bill() {
				{
					setDenomination(10);
					setBillName("$10");
				}
			}, 10);
			billsDB.put(new Bill() {
				{
					setDenomination(20);
					setBillName("$20");
				}
			}, 10);
			billsDB.put(new Bill() {
				{
					setDenomination(100);
					setBillName("$100");
				}
			}, 10);
		}
	}

	/**
	 * clear db with bills. useful for cases like teller machine is down
	 */
	private void clearInventory() {
		if (null != billsDB) {
			billsDB.clear();
		}
	}

	/**
	 * restock the db with default values this is a public method so make sure
	 * the repo is initialized
	 */
	public void reStock() {
		if (null == inventoryRepo) {
			getBillInventory();
		}
		inventoryRepo.clearInventory();
		inventoryRepo.initInventory();
	}

	/**
	 * @return billsDB Map
	 */
	public TreeMap<Bill, Integer> getBillsDB() {
		return billsDB;
	}

	/**
	 * return singleton object for bill inventory ps note this is not
	 * synchronized
	 * 
	 * @return BillInventory
	 */
	public static BillInventory getBillInventory() {
		if (inventoryRepo == null) {
			inventoryRepo = new BillInventory();
		}
		return inventoryRepo;
	}
}
