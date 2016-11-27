package hackApplication;

public class Permute {
	static int permute(java.util.List<Point> arr, int k) {

		int[] permutationsResults = new int[arr.size() - k];
		int currentPermut = 0;
		for (int i = k; i < arr.size(); i++) {
			java.util.Collections.swap(arr, i, k);

			if (arr.size() > k + 1) {
				permutationsResults[currentPermut] = permute(arr, k + 1);
				currentPermut++;
			}

			java.util.Collections.swap(arr, k, i);
		}

		if (arr.size() == k + 1) {
			return MainBottles.getRoadDistance(arr.toArray(new Point[0]));
		}

		return min(permutationsResults);
	}

	public static int min(int[] ar) {
		int m = ar[0];
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] < m) {
				m = ar[i];
			}
		}
		return m;

	}

}
