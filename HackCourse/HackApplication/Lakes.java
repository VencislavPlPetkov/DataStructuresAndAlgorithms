package hackApplication;

import java.util.Scanner;

public class Lakes {

	public static void main(String[] args) {

		int waterOnTop = 0;
		int volume = 0;

		Scanner input = new Scanner(System.in);
		String path = input.next();

		for (int i = 0; i < path.length(); i++) {

			if (path.charAt(i) == 'd') {
				waterOnTop++;
			}

			if (path.charAt(i) == 'u') {
				waterOnTop--;
			}
			if (waterOnTop > 0) {
				volume += waterOnTop;
			}

		}
		System.out.println(volume * 1000);
	}

}
