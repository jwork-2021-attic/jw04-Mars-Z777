package com.anish.calabashbros;

import java.lang.reflect.Array;

public class Matrix<T> {

	private int size;
	private T[][] m;
	private Class<T> TClass;
	
	public Matrix(Class<T> c, int size) {
		this.size = size;
		TClass = c;
		Object arr = Array.newInstance(c, size, size);
		m = (T[][])arr;
	}
	
	public void setT(T t, int x, int y) {
		if(0 <= x && x < size && 0 <= y && y < size) {
			m[x][y] = t;
		}
	}
	
	public T getT(int x, int y) {
		return m[x][y];
	}
	
	
	public int getSize() {
		return size;
	}
	
	public Class<T> getTClass(){
		return TClass;
	}
}
