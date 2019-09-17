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
import com.vane.ophthacare.model.Report;

public class SheetReport extends SheetInterface {

	public static final String SHEET_NAME = "Reports OphthaCare Clinique";
	private final String TITLE = "OphthaCare Clinique Reports";
	private final short indexColorFont = IndexedColors.BLACK.getIndex();
	private final short indexColorBackground = IndexedColors.YELLOW.getIndex();

	private Class classForMethod = Report.class;
	private Workbook workbook;
	private List<Report> reports;
	private CellStyle dataCellStyle;
	private ArrayList<ExcelObjectList> excelObjectList;

	private int titleStart = 1;
	private int headerStart = 3;

	public SheetReport() {
		super();
	}

	public SheetReport(Workbook workbook, List<Report> reportList) {
		this.workbook = workbook;
		this.reports = reportList;
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

		for (Report report : reports) {
			populateCell(sheet, excelObjectList, report, rowStart, dataCellStyle);
			rowStart++;
		}
		autoSize(sheet, excelObjectList.size());
	}
}
