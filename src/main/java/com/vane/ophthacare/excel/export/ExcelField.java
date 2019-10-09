package com.vane.ophthacare.excel.export;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {
	public String field();

	public int position();
}
