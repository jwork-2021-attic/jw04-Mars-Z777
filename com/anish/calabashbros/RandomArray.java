package com.anish.calabashbros;

public class RandomArray {

	public static int[] randomArray(int[] a){
		int[] b = new int[a.length];
		for(int i = 0; i < a.length; i++){
			int rIndex = (int)(Math.random() * (a.length - i));
			b[i] = a[rIndex];
			int tmp = a[a.length - i - 1];
			a[a.length - i - 1] = a[rIndex];
			a[rIndex] = tmp;
		}
		return b;
	}
	
}
