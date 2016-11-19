package dataStructues;

import java.util.Arrays;

public class Heap {

	private Data[] theHeap;
	private int itemsInArray;
	private int maxSize;

	public Heap(int maxSize) {

		this.maxSize = maxSize;
		theHeap = new Data[maxSize];
	}

	public void insert(int index, Data newData) {
		theHeap[index] = newData;
	}

	public void incrementTheArray() {
		itemsInArray++;
	}

	public Data pop() {

		int tempItemsInTheArray = itemsInArray - 1;

		// Display the data
		// System.out.println("Store " + theHeap[0] + " in root. Store " +
		// theHeap[tempItemsInTheArray] + " in index 0.");

		// System.out.println(Arrays.toString(theHeap) + "\n");

		Data root = theHeap[0];

		/*
		 * putting the last value of the array at the root, as a placeholder for
		 * the deleted element. Then sinking it down to it's natural position.
		 */

		theHeap[0] = theHeap[--itemsInArray];

		heapTheArray(0);

		return root;
	}

	private void heapTheArray(int index) {
		/*
		 * Comparing the two children and deciding which one is bigger.
		 * Comparing the bigger child with the parent, and if it is bigger they
		 * change places.
		 */

		int largestChild;
		Data root = theHeap[index];

		while (index < itemsInArray / 2) {
			int leftChild = 2 * index + 1;
			int rightChild = leftChild - 1;

			if (rightChild < itemsInArray && theHeap[leftChild].key < theHeap[rightChild].key) {

				// System.out.println("Put value of the right child " +
				// theHeap[rightChild] + " in largestChild");
				largestChild = rightChild;
			} else {
				// System.out.println("Put value of the left child " +
				// theHeap[leftChild] + " in largestChild");
				largestChild = leftChild;

			}

			if (root.key >= theHeap[largestChild].key) {
				break;
			}

			// System.out.println("Put index value of the largest child " +
			// theHeap[largestChild] + " in index " + index);

			// Save the value in largest child into the top index

			theHeap[index] = theHeap[largestChild];

			index = largestChild;

			// System.out.println(Arrays.toString(theHeap));

		}

		theHeap[index] = root;
	}

	private void generateFilledArray(int randNum) {

		Data randomData;

		for (int i = 0; i < this.maxSize; i++) {

			randomData = new Data((int) (Math.random() * randNum) + 1);
			this.insert(i, randomData);

			incrementTheArray();

		}

	}
	/*
	 * The array already satisfies the heap order property because of the method
	 * heapTheArray(). HeapSort() iterates over the array, and pops off each
	 * element. At the end the heap is sorted from smallest to largest.
	 */

	public void heapSort() {

		for (int k = maxSize - 1; k >= 0; k--) {

			// The pop method heaps the array on every calling
			Data largestNode = pop();
			insert(k, largestNode);
			// System.out.println(Arrays.toString(theHeap));
		}

	}

	public static void main(String[] args) {

		Heap newHeap = new Heap(7);
		newHeap.generateFilledArray(70);
		System.out.println("Original Array");
		System.out.println(Arrays.toString(newHeap.theHeap));

		for (int j = newHeap.maxSize / 2 - 1; j >= 0; j--) {

			newHeap.heapTheArray(j);

		}

		System.out.println("Heaped Array");
		System.out.println(Arrays.toString(newHeap.theHeap));

		newHeap.heapSort();

		System.out.println("Sorted Array");
		System.out.println(Arrays.toString(newHeap.theHeap));

	}

}

class Data {
	public int key;

	public Data(int key) {
		this.key = key;
	}

	public String toString() {
		return Integer.toString(key);
	}

}