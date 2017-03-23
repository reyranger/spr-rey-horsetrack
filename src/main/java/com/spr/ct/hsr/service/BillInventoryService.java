package com.spr.ct.hsr.service;

import java.util.List;

import com.spr.ct.hsr.exception.InsufficientFundsException;

/**
 * @author R.E.Y
 *
 */
public interface BillInventoryService {
	public void reStock();
	public List<String> payOut(int payoutCash) throws InsufficientFundsException;
	public List<String> getCurrentInventory();
	public int getInventoryTotal();
}
