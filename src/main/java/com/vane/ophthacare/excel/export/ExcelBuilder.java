package com.vane.ophthacare.excel.export;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vane.ophthacare.excel.sheet.SheetAdministrateur;
import com.vane.ophthacare.excel.sheet.SheetMaladie;
import com.vane.ophthacare.excel.sheet.SheetMedecin;
import com.vane.ophthacare.excel.sheet.SheetPatient;
import com.vane.ophthacare.excel.sheet.SheetProfessionMedecin;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.Maladie;
import com.vane.ophthacare.model.Medecin;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.model.ProfessionMedecin;

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

	public static Workbook buildExcelMedecin(List<Medecin> medecinList) {
		Workbook workbook = new XSSFWorkbook();

		SheetMedecin sheetMedecin = new SheetMedecin(workbook, medecinList);
		sheetMedecin.createSheet();
		
		return workbook;
	}

	public static Workbook buildExcelProfessionMedecin(List<ProfessionMedecin> professionMedecinList) {
		Workbook workbook = new XSSFWorkbook();

		SheetProfessionMedecin sheetProfessionMedecin = new SheetProfessionMedecin(workbook, professionMedecinList);
		sheetProfessionMedecin.createSheet();
		
		return workbook;
	}

}
