package com.anish.calabashbros;

import java.lang.reflect.Array;

public class SelectSorter<T extends Comparable<T>> implements Sorter<T> {

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
    
    private int maxIndex(int size) {
		if(size == 1) return 0;
		int res = 0;
		for(int i = 1; i < size; i++) {
			if(a[i].compareTo(a[res]) > 0)
				res = i;
		}
		return res;
	}

    private String plan = "";

    @Override
    public void sort() {
    	int len = a.length;
		while(len > 1) {
			int mIndex = maxIndex(len);
			len--;
			if(mIndex != len)
				swap(mIndex, len);
		}
    }

    @Override
    public String getPlan() {
        return this.plan;
    }
}
