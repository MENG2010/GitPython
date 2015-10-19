package edu.sc.cse.algs.sortInt;

/**
 * 
 * @author meng
 *
 */
public class QuickSort {
	public int[] sort(int[] unsorted, int low, int high) {
		int start = low, end = high;
		int pivot = unsorted[(low+high)/2];
		int tmp;
		
		while (start <= end) {
			while (unsorted[start] < pivot) {
				start++;
			}
			while (unsorted[end] > pivot) {
				end--;
			}
			
			if (start <= end) {
				tmp = unsorted[start];
				unsorted[start] = unsorted[end];
				unsorted[end] = tmp;
				start++;
				end--;
			}
		}
		
		if (low < end) {
			sort(unsorted, low, end);
		}
		
		if (start < high) {
			sort(unsorted, start, high);
		}
		
		return unsorted;
	}
}
