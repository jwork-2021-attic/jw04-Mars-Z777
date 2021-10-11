package com.anish.calabashbros;

import java.lang.reflect.Array;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[] a;

    @Override
    public void load(T[] a) {
        this.a = a;
    }
    
    @Override
    public void loadMatrix(Matrix<T> m) {
    	int s = m.getSize();
    	int n = s * s;
    	Class<T> TClass = m.getTClass();
    	Object arr = Array.newInstance(TClass, n);
    	a = (T[])arr;
    	for(int i = 0; i < n; i++)
    		a[i] = m.getT(i / s, i % s);
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i].compareTo(a[i + 1]) > 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}