package multithreadingPart01;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Producer Consumer Pattern

public class MainBlockingQueue {
	// FIFO- First In First Out - The oldest are removed first
	// It is thread safe so we don't need to worry about thread
	// synchronisation.
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void producer() throws InterruptedException {
		Random random = new Random();

		while (true) {
			queue.put(random.nextInt(100));

		}
	}

	private static void consumer() throws InterruptedException {
		while (true) {
			Random random = new Random();

			Thread.sleep(100);

			if (random.nextInt(10) == 0) {
				Integer value = queue.take();

				System.out.println("Taken value: " + value + "; Queue size is " + queue.size());
			}

		}
	}

}
