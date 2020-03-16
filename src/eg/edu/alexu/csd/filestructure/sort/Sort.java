package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Sort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public IHeap<T> heapSort(ArrayList<T> unordered) {
		Heap<T> heapsort = new Heap<T>();
		heapsort.build(unordered);
		int n = heapsort.size();
		for (int i = n - 1; i > 0; i--) {
			heapsort.RemoveforSort(i);
			heapsort.heapify(heapsort.getRoot());
		}
		heapsort.setsize();
		// heapsort.reverse();
		return heapsort;
	}

	@Override
	public void sortSlow(ArrayList<T> unordered) {
		if (unordered == null || unordered.size() == 0)
			return;
		boolean sorted = false;
		Object arr[] = unordered.toArray();
		for (int i = 0; i < arr.length && !sorted; i++) {
			sorted = true;
			for (int j = 0; j < arr.length - 1; j++) {
				if (((T) arr[j]).compareTo((T) arr[j + 1]) > 0) {
					sorted = false;
					T temp1 = (T) arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp1;
				}
			}
		}
		unordered.clear();
		for (int i = 0; i < arr.length; i++) {
			unordered.add((T) arr[i]);
		}

	}

	@Override
	public void sortFast(ArrayList<T> unordered) {
		if (unordered == null || unordered.size() == 0)
			return;
		Object arr[] = unordered.toArray();
		int flag = checkSorting(arr); // System.out.println(flag);
		if (flag == 0)
			return;
		if (flag == 1) {
			reverse(arr);
		} else
			quickSort(arr, 0, arr.length - 1);
		unordered.clear();
		for (int i = 0; i < arr.length; i++) {
			unordered.add((T) arr[i]);
		}
	}

	int getPivot(Object arr[], int l, int r) {
		T p = (T) arr[r];
		int i = l - 1;
		for (int j = l; j < r; j++) {
			if (((Comparable<T>) arr[j]).compareTo(p) < 0) {
				i++;
				swap(i, j, arr);
			}
		}
		swap(i + 1, r, arr);
		return (i + 1);
	}

	void quickSort(Object arr[], int l, int r) {
		if (l >= r)
			return;

		int p = getPivot(arr, l, r);
		quickSort(arr, l, p - 1);
		quickSort(arr, p + 1, r);

	}

	private int checkSorting(Object[] arr) {
		int flag = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			if (((Comparable<T>) arr[i]).compareTo((T) arr[i + 1]) > 0)
				flag = 1;
			if (((Comparable<T>) arr[i]).compareTo((T) arr[i + 1]) < 0 && flag == 1) {
				flag = 2;
				break;
			}
		}
		return flag;
	}

	void reverse(Object arr[]) {
		int size = arr.length;
		for (int i = 0; i < (size) / 2; i++) {
			swap(i, size - i - 1, arr);
		}
	}

	private void swap(int x, int y, Object arr[]) {
		T temp = (T) arr[x];
		arr[x] = arr[y];
		arr[y] = temp;

	}
}
