package com.vane.ophthacare.excel.sheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.vane.ophthacare.excel.export.ExcelObjectList;
import com.vane.ophthacare.excel.export.SheetInterface;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.Maladie;

public class SheetMaladie extends SheetInterface {

	public static final String SHEET_NAME = "Maladies OphthaCare Clinique";
	private final String TITLE = "OphthaCare Clinique Maladies";
	private final short indexColorFont = IndexedColors.BLACK.getIndex();
	private final short indexColorBackground = IndexedColors.YELLOW.getIndex();

	private Class classForMethod = Maladie.class;
	private Workbook workbook;
	private List<Maladie> maladies;
	private CellStyle dataCellStyle;
	private ArrayList<ExcelObjectList> excelObjectList;

	private int titleStart = 1;
	private int headerStart = 3;

	public SheetMaladie() {
		super();
	}

	public SheetMaladie(Workbook workbook, List<Maladie> maladieList) {
		this.workbook = workbook;
		this.maladies = maladieList;
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

		for (Maladie maladie : maladies) {
			populateCell(sheet, excelObjectList, maladie, rowStart, dataCellStyle);
			rowStart++;
		}
		autoSize(sheet, excelObjectList.size());
	}
}
