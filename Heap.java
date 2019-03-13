package heapppkg;

import java.util.ArrayList;
import java.util.List;

public class Heap {

	private List<Integer> list;

	public Heap() {
		list = new ArrayList<Integer>();
	}

	public void add(Integer object) {
		// Add new object and sift up; check the parents for swap.
		list.add(object);
		int current = list.size() - 1;
		siftUp(current);
	}

	private void swap(int indexOne, int indexTwo) {
		int firstValue = list.get(indexOne);
		int secondValue = list.get(indexTwo);
		list.set(indexOne, firstValue);
		list.set(indexTwo, secondValue);
	}

	public void remove() {
		// Remove root by replacing the value with the bottom-most
		int bottomValue = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		list.set(0, bottomValue);
		siftDown(0);
	}

	// Keep swapping child with parent until fixed.
	private void siftUp(int current) {
		boolean isHeapFixed = false;
		while (!isHeapFixed) {
			int parent;
			
			// Check if current is root.
			if (current != 0) {
				parent = getParent(current);
				
				// Compare the current with the parent
				// Swap if not yet found a parent of greater value.
				if (list.get(current) > list.get(parent)) {				
					swap(current, parent);
					current = parent;
				} else {
					isHeapFixed = true;
				}
			} else {
				isHeapFixed = true;				
			}			
		}
	}

	private void siftDown(int current) {
		boolean isHeapFixed = false;

		while (!isHeapFixed) {
			int leftChild = getLeftChild(current);
			int rightChild = getRightChild(current);
			int leftValue;
			int rightValue;
			int chosenChild;

			// Check if the current has children.
			// If a child does not exist, set it to smallest integer value.
			if (leftChild > list.size()) {
				leftValue = Integer.MIN_VALUE;
			} else {
				leftValue = list.get(leftChild);
			}

			if (rightChild > list.size()) {
				rightValue = Integer.MIN_VALUE;
			} else {
				rightValue = list.get(rightChild);
			}

			// If there is at least 1 valid child, make the larger one the chosen.
			// Then swap and repeat. Otherwise the heap is fixed.
			if (leftValue != Integer.MIN_VALUE || rightChild != Integer.MIN_VALUE) {
				chosenChild = (leftValue > rightValue ? leftChild : rightChild);
				swap(current, chosenChild);
				current = chosenChild;
			} else {
				isHeapFixed = true;
			}
		}
	}

	public void heapSort() {
		// TODO:
	}

	private int getParent(int index) {
		return (int) Math.floor((index - 1) / 2);
	}

	private int getLeftChild(int index) {
		return ((2 * index) + 1);
	}

	private int getRightChild(int index) {
		return ((2 * index) + 2);
	}
}