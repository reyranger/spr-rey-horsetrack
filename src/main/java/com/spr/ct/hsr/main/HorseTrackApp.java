package com.spr.ct.hsr.main;

import java.util.List;
import java.util.Scanner;

import com.spr.ct.hsr.exception.InvalidBetException;
import com.spr.ct.hsr.exception.InvalidCommandException;
import com.spr.ct.hsr.service.BillInventoryService;
import com.spr.ct.hsr.service.BillInventoryServiceImpl;
import com.spr.ct.hsr.service.HorseRepoService;
import com.spr.ct.hsr.service.HorseRepoServiceImpl;
import com.spr.ct.hsr.validate.InputValidator;

/**
 * @author R.E.Y
 *
 */
public class HorseTrackApp {
	private static BillInventoryService billInventoryService = new BillInventoryServiceImpl();
	private static HorseRepoService horseRepoService = new HorseRepoServiceImpl();
	private static InputValidator inputValidator = new InputValidator();

	public static void init() {
		System.out.println("Inventory:");
		billInventoryService.getCurrentInventory().stream().forEach(System.out::println);
		System.out.println("Horses:");
		horseRepoService.getCurrentHorseStandings().stream().forEach(System.out::println);
	}

	public static void runApp(String commands) {

		boolean isValidated = false;
		// validate input commands
		try {
			isValidated = inputValidator.validate(commands);
		} catch (InvalidCommandException | InvalidBetException e) {
			System.out.println(e.getMessage());
		}

		// if validated then perform action
		if (isValidated) {
			String[] cmds = commands.trim().split(" ");
			String firstCommand = cmds[0].toUpperCase();
			switch (firstCommand) {
			case "Q":
				break;
			case "R":
				billInventoryService.reStock();
				init();
				break;
			case "W":
				horseRepoService.markWinningHorse(Integer.parseInt(cmds[1]));
				init();
				break;

			default:
				String horseNumber = firstCommand;
				String bet = cmds[1];
				init();
				if (Integer.parseInt(firstCommand) == horseRepoService.getCurrentWinningHorse().getHorseId()) {
					int payoutCash = horseRepoService.calculatePayout(Integer.parseInt(bet));
					System.out.println("Payout : " + horseRepoService.getCurrentWinningHorse().getHorseName() + ", $"
							+ payoutCash);
					List<String> dispensedBills = billInventoryService.payOut(payoutCash);
					dispensedBills.forEach(System.out::println);

				} else {
					System.out.println("No Payout: "
							+ horseRepoService.getHorseDetails(Integer.parseInt(horseNumber)).getHorseName());
				}
				init();
				break;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String commands = scanner.next();
			runApp(commands);
			String[] comms = commands.split(" ");
			if (comms[0].equalsIgnoreCase("q")) {
				break;
			}
		}
		scanner.close();
		System.exit(-1);
	}
}
