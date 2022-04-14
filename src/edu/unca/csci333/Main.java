package edu.unca.csci333;
import java.util.*;

/*
 * CSCI 333
 * Homework 4
 * 2/19/2022
 * Code Description: Sorts a given integer array using a 
 * counting sort then finds the 'ith' smallest element using randomized quickselect
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {11,5,0,2,10,15,50};
		int[] b = {7,3,16,1,8};
		int[] c = {4,3,5};
		int[] d = {2,1};
		int[] e = {10,10,5,4,5};
		
		int[][] test = {a,b,c,d,e};
		int[] kval = {50,16,5,2,10};
		
		System.out.println("Counting Sort Test");
		for(int i = 0; i < test.length; i++) {	
			int[] out = new int[test[i].length];
			System.out.print("\nInput: ");
			System.out.println(Arrays.toString(test[i]));
			System.out.print("Output: ");
			countingSort(test[i],out,kval[i]);
			System.out.println(Arrays.toString(out));
		}	
		int[] ivals = {6,5,1,2,3};
		System.out.println("\nRandomized Quickselect Test");
		for(int i = 0; i < test.length; i++) {
			int high = test[i].length - 1;
			int low = 0;
			int out= randomizedQuickselectWrapper(test[i], low, high, ivals[i]);
			System.out.println("\nInput: " + Arrays.toString(test[i]));
			System.out.println("The "+ivals[i]+"th smallest element of the input is: "+out);
		}
	}
	
	public static void countingSort(int[] a, int[] b, int k) {
		int[] c = new int[k+1];
		for(int i = 0; i < c.length; i++) { // initialize counting array
			c[i] = 0;
		}
		for(int i = 0; i < a.length; i++) {
			c[a[i]]++;
		}
		for(int i = 1; i < c.length; i++) {
			c[i] = c[i] + c[i-1];
		}
		for(int i = a.length-1; i >= 0; i--) {
			b[c[a[i]]-1] = a[i];
			c[a[i]]--;
		}
	}
	
	public static int randomizedQuickselectWrapper(int[]a, int low, int high, int i) {
		int[] local = Arrays.copyOf(a, a.length);
		return randomizedQuickselect(local,low,high,i);
	}
	public static int randomizedQuickselect(int[] a, int low, int high, int i) {
		if(low == high) {
			return a[low];
		}
		Random rand = new Random();  // choose random value as partition
		int z = rand.nextInt(high-low + 1) + low;
		swap(a, low, z);
		int q = partition(a,low,high);
		int k = q-low+1;
		if(i==k) { // pivot value is the answer
			return a[q];
		} 
		else if(i<k) {
			return randomizedQuickselect(a,low,q-1,i);
		}
		else return randomizedQuickselect(a,q+1,high,i-k);
	}
	
	private static int partition(int[] A, int p, int r) {
		int x = A[r];	// choose pivot value as right-most element
		int i = p-1;
		for(int j = p; j < r; j++) {
			if(A[j] <= x) {
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i+1, r);
		return i+1;
	}
	
	private static void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}
