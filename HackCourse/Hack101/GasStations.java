import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class GasStations {

	public static void main(String[] args) {
		int tripDistance = 320;
		int tankSize = 90;
		Vector<Integer> result = new Vector();

		Integer[] arr = { 50, 80, 140, 180, 220, 290, 320 };

		int currTankSize = tankSize - arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			int future = Math.abs(arr[i] - arr[i + 1]);
			if (currTankSize < future) {
				System.out.println(arr[i]);
				currTankSize = tankSize;
				currTankSize -= future;
			} else {
				currTankSize -= future;
			}

		}

	}

}
