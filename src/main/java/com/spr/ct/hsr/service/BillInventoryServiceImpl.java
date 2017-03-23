package com.spr.ct.hsr.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.spr.ct.hsr.bean.Bill;
import com.spr.ct.hsr.db.BillInventory;
import com.spr.ct.hsr.exception.InsufficientFundsException;

/**
 * @author R.E.Y
 * 
 *         Provides service methods for BillInventory repository
 * 
 */
public class BillInventoryServiceImpl implements BillInventoryService {

	final static Logger LOGGER = Logger.getLogger(BillInventoryServiceImpl.class);

	private BillInventory billInventory = BillInventory.getBillInventory();
	private TreeMap<Bill, Integer> billsMap = billInventory.getBillsDB();
	private List<String> auditList = new ArrayList<String>();
	private List<String> inventoryList = new ArrayList<String>();

	/**
	 * reStock action when inventory is out of cash
	 */
	@Override
	public void reStock() {
		billInventory.reStock();
	};

	/**
	 * payOut action for the prize money
	 * 
	 * @param payoutCash
	 * @throws InsufficientFundsException
	 */
	@Override
	public List<String> payOut(int payoutCash) throws InsufficientFundsException {
		if (payoutCash > getInventoryTotal()) {
			throw new InsufficientFundsException();
		} else {
			return calculateOptimumBillDispense(payoutCash);
		}
	}

	/**
	 * calculate minimum bills dispense based on available cash/bills
	 * 
	 * @param payoutCash
	 */
	private List<String> calculateOptimumBillDispense(int payoutCash) {

		auditList.clear();
		for (Map.Entry<Bill, Integer> entry : billsMap.descendingMap().entrySet()) {
			int currentBill = entry.getKey().getDenomination();
			String currentBillName = entry.getKey().getBillName();
			int currentBillCount = entry.getValue();
			if (payoutCash > 0) {
				if (currentBillCount > 0) {
					int reqCurrentBillCount = payoutCash / currentBill;
					int billRemainder = payoutCash % currentBill;

					if (reqCurrentBillCount != 0 && billRemainder != 0) {

						// if current bill count is greater than required bill
						if (currentBillCount >= reqCurrentBillCount) {
							// pay off maximum with current bill, remaining
							// residue will pay by next bill
							payoutCash = payoutCash - (reqCurrentBillCount * currentBill);
							// set new available bill count in db
							entry.setValue(currentBillCount - reqCurrentBillCount);
							// add audit record in list
							auditList.add(currentBillName + ", " + reqCurrentBillCount);
						} else {
							/// lets pay maximum with all remaining bills
							payoutCash = payoutCash - (currentBillCount * currentBill);
							// all current bills must be exhausted in this case,
							// update db
							entry.setValue(0);
							// add audit record in list
							auditList.add(currentBillName + ", " + currentBillCount);
						}
					} else if (reqCurrentBillCount != 0 && billRemainder == 0) {

						// if current bill count is greater than required bill
						if (currentBillCount >= reqCurrentBillCount) {
							// pay off completely with current bill,
							// below payoutCash should be zero in this case
							payoutCash = payoutCash - (reqCurrentBillCount * currentBill);
							// set new available bill count in db
							entry.setValue(currentBillCount - reqCurrentBillCount);
							// add audit record in list
							auditList.add(currentBillName + ", " + reqCurrentBillCount);
						} else {

							// lets pay maximum with all remaining bills
							payoutCash = payoutCash - (currentBillCount * currentBill);
							// all current bills must be exhausted in this case,
							// update db
							entry.setValue(0);
							// add audit record in list
							auditList.add(currentBillName + ", " + currentBillCount);
						}
					} else if (reqCurrentBillCount == 0 && billRemainder != 0) {
						// there some remainder to be paid out
						// go to next denomination
						// add current denomination to the list with 0
						auditList.add(currentBillName + ", " + 0);

					} else {
						// if (reqCurrentBillCount == 0 && billRemainder == 0)
						// added for readability
						// in this case remaining payoutCash should be 0,
						// however add current denomination to the list with 0
						auditList.add(currentBillName + ", " + 0);
					}

				} else {
//					System.out.println("TELLER MACHINE OUT OF BILLS :: " + currentBill);
					// add current denomination to the list with 0
					auditList.add(currentBillName + ", " + 0);
				}
			} else {
//				System.out.println("TOTAL PAYOUT DISPENSED :: ");
				// add current denomination to the list with 0				
				auditList.add(currentBillName + ", " + 0);
			}
		}
		// auditList insertion order should be reversed
		Collections.reverse(auditList);
		return auditList;
	}

	/**
	 * get current Inventory of bills
	 */
	@Override
	public List<String> getCurrentInventory() {
		inventoryList = billsMap.entrySet().stream()
				.sorted((b1, b2) -> b1.getKey().getDenomination() - b2.getKey().getDenomination())
				.map(e -> ((Bill) e.getKey()).getBillName() + ", " + e.getValue()).collect(Collectors.toList());
		return inventoryList;
	}

	/**
	 * Calculate Current Inventory Total
	 * 
	 * @return inventoryTotal value
	 */
	@Override
	public int getInventoryTotal() {
		int totalInventory = 0;

		if (null != billInventory.getBillsDB() && billInventory.getBillsDB().size() > 0)
			totalInventory = billsMap.entrySet().stream().map(e -> e.getValue() * ((Bill) e.getKey()).getDenomination())
					.collect(Collectors.toList()).stream().mapToInt(Integer::intValue).sum();

		return totalInventory;
	}

}
