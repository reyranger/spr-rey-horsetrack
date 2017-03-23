package com.spr.ct.hsr.service;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.spr.ct.hsr.exception.InsufficientFundsException;

import junit.framework.TestCase;

/**
 * @author R.E.Y
 *
 */
public class BillInventoryServiceTest extends TestCase {

	private BillInventoryService billInventoryService = new BillInventoryServiceImpl();
	final static Logger LOGGER = Logger.getLogger(BillInventoryServiceTest.class);

	@Test
	public void testGetCurrentInventory() {
		billInventoryService.getCurrentInventory().stream().forEach(System.out::println);
	}

	@Test
	public void testGetInventoryTotal() {
		System.out.println(billInventoryService.getInventoryTotal());
		assertNotSame(billInventoryService.getInventoryTotal(), 1366);
		assertEquals(billInventoryService.getInventoryTotal(), 1360);
	}

	@Test
	public void testPayOut() {
		try {
			System.out.println("TO PAY # 1045");
			billInventoryService.payOut(1045).stream().forEach(System.out::println);
			System.out.println("" + billInventoryService.getInventoryTotal());
			assertEquals(billInventoryService.getInventoryTotal(), 315);
			System.out.println("\n------\n");

			billInventoryService.reStock();
			System.out.println("TO PAY # 236");
			billInventoryService.payOut(236).stream().forEach(System.out::println);
			System.out.println("" + billInventoryService.getInventoryTotal());
			assertEquals(billInventoryService.getInventoryTotal(), 1124);
			System.out.println("\n------\n");

			billInventoryService.reStock();
			System.out.println("TO PAY # 152");
			billInventoryService.payOut(152).stream().forEach(System.out::println);
			System.out.println("" + billInventoryService.getInventoryTotal());
			assertEquals(billInventoryService.getInventoryTotal(), 1208);
			System.out.println("\n------\n");

			billInventoryService.reStock();
			System.out.println("TO PAY # 1360");
			billInventoryService.payOut(1360).stream().forEach(System.out::println);
			System.out.println("" + billInventoryService.getInventoryTotal());
			assertEquals(billInventoryService.getInventoryTotal(), 0);
			System.out.println("\n------\n");

			billInventoryService.reStock();
			System.out.println("" + billInventoryService.getInventoryTotal());
			assertEquals(billInventoryService.getInventoryTotal(), 1360);

		} catch (InsufficientFundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insufficientFundsExceptionTest() throws InsufficientFundsException {
		Assert.fail(billInventoryService.payOut(1360).toString());
	}

}
