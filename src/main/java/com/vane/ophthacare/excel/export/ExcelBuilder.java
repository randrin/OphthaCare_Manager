package com.vane.ophthacare.excel.export;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vane.ophthacare.excel.sheet.SheetPatient;
import com.vane.ophthacare.model.Patient;

public class ExcelBuilder {

	public static Workbook buildExcelPatient(List<Patient> patients) {
		Workbook workbook = new XSSFWorkbook();

		SheetPatient sheetPatient = new SheetPatient(workbook, patients);
		sheetPatient.createSheet();
		
		return workbook;
	}

}
