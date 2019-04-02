package com.vane.ophthacare.excel.export;

import java.lang.reflect.Method;

public class ExcelObjectList implements Comparable {
	private int position;
	private String nameField;
	private Method methodCaller;
	
	public ExcelObjectList(int position, String nameField, Method methodCaller) {
		super();
		this.position = position;
		this.nameField = nameField;
		this.methodCaller = methodCaller;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public Method getMethodCaller() {
		return methodCaller;
	}

	public void setMethodCaller(Method methodCaller) {
		this.methodCaller = methodCaller;
	}

	@Override
	public int compareTo(Object o) {
		int comparePosition=((ExcelObjectList)o).getPosition();
        return this.position-comparePosition;
	}
}
