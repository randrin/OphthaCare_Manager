package com.vane.ophthacare.excel.sheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.vane.ophthacare.excel.export.ExcelObjectList;
import com.vane.ophthacare.excel.export.SheetInterface;
import com.vane.ophthacare.model.ProfessionMedecin;

public class SheetProfessionMedecin extends SheetInterface {

	public static final String SHEET_NAME = "Professions Médecins OphthaCare Clinique";
	private final String TITLE = "OphthaCare Clinique Médecins Professions";
	private final short indexColorFont = IndexedColors.BLACK.getIndex();
	private final short indexColorBackground = IndexedColors.YELLOW.getIndex();

	private Class classForMethod = ProfessionMedecin.class;
	private Workbook workbook;
	private List<ProfessionMedecin> professionMedecins;
	private CellStyle dataCellStyle;
	private ArrayList<ExcelObjectList> excelObjectList;

	private int titleStart = 1;
	private int headerStart = 3;

	public SheetProfessionMedecin() {
		super();
	}

	public SheetProfessionMedecin(Workbook workbook, List<ProfessionMedecin> profesionMedecinList) {
		this.workbook = workbook;
		this.professionMedecins = profesionMedecinList;
	}

	public void createSheet() {
		Sheet sheet = workbook.createSheet(SHEET_NAME);
		excelObjectList = getAllGetterMethod(classForMethod);
		createSheetData(workbook, sheet, excelObjectList, TITLE, indexColorFont, indexColorBackground, titleStart,
				headerStart);
		startingPopulate(sheet);
	}

	public void startingPopulate(Sheet sheet) {
		int rowStart = rowNumDataCelStart;
		dataCellStyle = workbook.createCellStyle();
		borderCell(dataCellStyle);

		for (ProfessionMedecin profressionMedecin : professionMedecins) {
			populateCell(sheet, excelObjectList, profressionMedecin, rowStart, dataCellStyle);
			rowStart++;
		}
		autoSize(sheet, excelObjectList.size());
	}
}
