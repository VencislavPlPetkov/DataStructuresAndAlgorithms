package multithreadingPart01;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import static java.util.concurrent.TimeUnit.*;

public class MainCheckSystemCheckTime {

	public static void main(String[] args) {

		addThreadsToPool();

	}

	private static void addThreadsToPool() {

		ScheduledThreadPoolExecutor eventPool = new ScheduledThreadPoolExecutor(5);

		eventPool.scheduleAtFixedRate(new CheckSystemTime(), 0, 2, SECONDS);
		eventPool.scheduleAtFixedRate(new PerformSystemCheck("Mail"), 5, 5, SECONDS);
		eventPool.scheduleAtFixedRate(new PerformSystemCheck("Calendar"), 10, 10, SECONDS);

		System.out.println("Number of threads: " + Thread.activeCount());

		Thread[] listOfThreads = new Thread[Thread.activeCount()];

		Thread.enumerate(listOfThreads);

		for (Thread i : listOfThreads) {
			System.out.println(i.getName());
		}

		// Check Priority of threads. All of them show 5, because that is the
		// default priority for the main method that calls all of them
		// 1 - lowest , 10 - highest.
		for (Thread i : listOfThreads) {
			System.out.println(i.getPriority());
		}

		// So that it doesn't run indefinitely
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
		}

	}

}
