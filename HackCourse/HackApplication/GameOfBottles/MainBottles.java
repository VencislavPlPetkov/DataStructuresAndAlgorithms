package hackApplication;

import java.util.Arrays;
import java.util.Scanner;

public class MainBottles {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int pointsCount = input.nextInt();
		Point[] points = new Point[pointsCount];

		for (int i = 0; i < points.length; i++) {
			points[i] = new Point();
			points[i].x = input.nextInt();
			points[i].y = input.nextInt();
		}

		int min = Permute.permute(Arrays.asList(points), 0);
		System.out.println(min);
	}

	public static int distanceBetween(Point A, Point B) {
		return Math.abs(A.x - B.x) + Math.abs(A.y - B.y);
	}

	public static int getRoadDistance(Point[] points) {

		int sum = 0;
		for (int i = 0; i < points.length - 1; i++) {
			sum += distanceBetween(points[i], points[i + 1]);
		}

		return sum;
	}

}
