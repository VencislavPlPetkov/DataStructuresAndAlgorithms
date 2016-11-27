package hackApplication;

import java.util.Scanner;

public class Heads {

	public static void main(String[] args) {

		String lastSide = "";
		String currToss = "";

		int maxH = 0;
		int maxT = 0;
		int conseq = 1;

		Scanner scn = new Scanner(System.in);
		String[] input = scn.nextLine().split(",");

		for (int i = 0; i < input.length; i++) {

			input[i] = input[i].trim();
		}

		for (String i : input) {
			currToss = new String(i);

			if (currToss.equalsIgnoreCase(lastSide)) {
				conseq++;
				if (conseq > maxH && currToss.equalsIgnoreCase("H")) {
					maxH = conseq;
				} else if (conseq > maxT && currToss.equalsIgnoreCase("T")) {
					maxT = conseq;
				}
			} else {
				conseq = 1;
			}

			lastSide = new String(currToss);
		}

		if (maxH > maxT) {
			System.out.println("H wins!");
		} else if (maxH < maxT) {
			System.out.println("T wins!");
		} else {
			System.out.println("Draw!");
		}
	}
}
