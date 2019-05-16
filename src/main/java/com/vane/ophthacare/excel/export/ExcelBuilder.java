package com.vane.ophthacare.excel.export;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vane.ophthacare.excel.sheet.SheetAdministrateur;
import com.vane.ophthacare.excel.sheet.SheetMaladie;
import com.vane.ophthacare.excel.sheet.SheetPatient;
import com.vane.ophthacare.excel.sheet.SheetSpecialiste;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.Maladie;
import com.vane.ophthacare.model.Specialiste;
import com.vane.ophthacare.model.Patient;

public class ExcelBuilder {

	public static Workbook buildExcelPatient(List<Patient> patients) {
		Workbook workbook = new XSSFWorkbook();

		SheetPatient sheetPatient = new SheetPatient(workbook, patients);
		sheetPatient.createSheet();
		
		return workbook;
	}
	
	public static Workbook buildExcelAdmin (List<Administrateur> admins) {
		Workbook workbook = new XSSFWorkbook();

		SheetAdministrateur sheetAdministrateur = new SheetAdministrateur(workbook, admins);
		sheetAdministrateur.createSheet();
		
		return workbook;
	}

	public static Workbook buildExcelMaladie(List<Maladie> maladies) {
		Workbook workbook = new XSSFWorkbook();

		SheetMaladie sheetMaladie = new SheetMaladie(workbook, maladies);
		sheetMaladie.createSheet();
		
		return workbook;
	}

	public static Workbook buildExcelMedecin(List<Specialiste> medecinList) {
		Workbook workbook = new XSSFWorkbook();

		SheetSpecialiste sheetMedecin = new SheetSpecialiste(workbook, medecinList);
		sheetMedecin.createSheet();
		
		return workbook;
	}

}
