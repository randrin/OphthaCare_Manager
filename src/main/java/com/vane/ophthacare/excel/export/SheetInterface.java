package com.vane.ophthacare.excel.export;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SheetInterface {

	public int rowNumDataCelStart = 4;
	public int headerStartDefault = 3;
	public static final Logger logger = LoggerFactory.getLogger(SheetInterface.class);
	private boolean addRow = false;

	public void createSheetData(Workbook workbook, Sheet sheet, ArrayList<ExcelObjectList> excelObjectList,
			String title, short indexColorFont, short indexColorBackground, int titleStart, int headerStart) {
		createTitleCell(workbook, sheet, title, excelObjectList.size(), indexColorFont, indexColorBackground,
				titleStart);
		createHeaderCell(workbook, sheet, title, excelObjectList, indexColorFont, indexColorBackground, headerStart);
	}

	public void createTitleCell(Workbook workbook, Sheet sheet, String title, int dimension, short indexColorFont,
			short indexColorBackground, int titleStart) {

		CellStyle titleCellStyle = workbook.createCellStyle();
		stylingCel(workbook, titleCellStyle, indexColorFont, indexColorBackground);

		Row headerRow = sheet.createRow(titleStart);
		Cell titleCel = headerRow.createCell(1);
		titleCel.setCellValue(title);
		titleCel.setCellStyle(titleCellStyle);

		for (int i = 2; i <= dimension; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(titleCellStyle);
		}
		sheet.addMergedRegion(new CellRangeAddress(titleStart, titleStart, 1, dimension));

	}

	public void createHeaderCell(Workbook workbook, Sheet sheet, String title,
			ArrayList<ExcelObjectList> excelObjectList, short indexColorFont, short indexColorBackground,
			int headerStart) {

		CellStyle headerCellStyle = workbook.createCellStyle();
		stylingCel(workbook, headerCellStyle, indexColorFont, indexColorBackground);
		Row headerRow = sheet.createRow(headerStart);

		for (int i = 1; i <= excelObjectList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(excelObjectList.get(i - 1).getNameField());
			cell.setCellStyle(headerCellStyle);
		}

		for (int i = 1; i <= excelObjectList.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}

	public void stylingCel(Workbook workbook, CellStyle cellStyle, short indexColorFont, short indexColorBackground) {
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);
		font.setColor(indexColorFont);
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(indexColorBackground);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		borderCell(cellStyle);
	}

	public void borderCell(CellStyle cellStyle) {
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	}

	public ArrayList<ExcelObjectList> getAllGetterMethod(Class c) {
		ArrayList<ExcelObjectList> list = new ArrayList<ExcelObjectList>();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && method.getAnnotation(ExcelField.class) != null) {
				try {
					ExcelObjectList excelObjectList = new ExcelObjectList(
							method.getAnnotation(ExcelField.class).position(),
							method.getAnnotation(ExcelField.class).field(), method);
					list.add(excelObjectList);
				} catch (Exception e) {
					logger.info("Error getting method for class " + c + "; message: " + e.getLocalizedMessage() + " "
							+ e.getCause() + " " + e.toString());
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	public ArrayList<ExcelObjectList> getAllSetterMethod(Class c) {
		ArrayList<ExcelObjectList> list = new ArrayList<ExcelObjectList>();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("set") && method.getAnnotation(ExcelField.class) != null) {
				method.getAnnotation(ExcelField.class);
				try {
					ExcelObjectList excelObjectList = new ExcelObjectList(
							method.getAnnotation(ExcelField.class).position(),
							method.getAnnotation(ExcelField.class).field(), method);
					list.add(excelObjectList);
				} catch (Exception e) {
					logger.info("Error getting method class for class " + c + "; message: " + e.getMessage());
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	public void createCell(CellStyle dataCellStyle, Row row, String data, int celNumber) {
		Cell cel = row.createCell(celNumber);
		cel.setCellValue(data);
		cel.setCellStyle(dataCellStyle);
	}

	public void populateCell(Sheet sheet, ArrayList<ExcelObjectList> methods, Object objectToInvoke, int rowNum,
			CellStyle dataCellStyle) {

		if (objectToInvoke == null) {
			return;
		}

		Row row = sheet.createRow(rowNum++);
		int cellToWrite = 1;
		for (ExcelObjectList method : methods) {
			try {
				String value = String.valueOf(method.getMethodCaller().invoke(objectToInvoke, null));
				if (value == null || value.equals("null")) {
					value = "";
				}
				createCell(dataCellStyle, row, value, cellToWrite);
				cellToWrite++;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	public void autoSize(Sheet sheet, int numColumns) {
		for (int i = 1; i <= numColumns; i++) {
			sheet.autoSizeColumn(i);
		}
	}

//	public ArrayList readExcelFile(Sheet sheet, int headerStart, ModelEntity t) {
//
//		ArrayList modelEntities = new ArrayList();
//		Class objectForMethod = t.getClass();
//		ArrayList<ExcelObjectList> excelObjectList = getAllSetterMethod(objectForMethod);
//
//		sheet.forEach(row -> {
//			ModelEntity modelEntityObject;
//			try {
//				modelEntityObject = (ModelEntity) objectForMethod.newInstance();
//				if (row.getRowNum() > headerStart) {
//					row.forEach(cell -> {
//						try {
//							String valueCell = getCellValueType(cell);
//
//							if (valueCell != null && valueCell != "") {
//								addRow = true;
//							}
//
//							Method methodToCall = excelObjectList.get(cell.getColumnIndex() - 1).getMethodCaller();
//							String methodTypeParams = methodToCall.getParameterTypes()[0].getCanonicalName();
//
//							if (methodTypeParams.contains("String")) {
//								excelObjectList.get(cell.getColumnIndex() - 1).getMethodCaller()
//										.invoke(modelEntityObject, valueCell);
//							} else {
//								excelObjectList.get(cell.getColumnIndex() - 1).getMethodCaller()
//										.invoke(modelEntityObject, (int) Double.parseDouble(valueCell));
//							}
//
//						} catch (IndexOutOfBoundsException e) {
//
//						} catch (Exception e2) {
//							logger.error("Error: " + sheet.getSheetName() + "\t rowNum:" + row.getRowNum()
//									+ "\t columnIndex:" + cell.getColumnIndex() + "\t" + e2.getMessage() + "\n" + e2);
//						}
//					});
//
//					if (addRow) {
//						modelEntities.add(modelEntityObject);
//						addRow = false;
//					}
//				}
//			} catch (InstantiationException | IllegalAccessException e2) {
//				logger.error("Error: " + sheet.getSheetName() + "\t rowNum:" + row.getRowNum() + "\t " + e2.getMessage()
//						+ "\n");
//				e2.printStackTrace();
//			}
//
//		});
//
//		return modelEntities;
//	}

	private String getCellValueType(Cell cell) {
		if (cell != null) {
			switch (cell.getCellTypeEnum()) {
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());

			case NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {

					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

					String dateString = format.format(cell.getDateCellValue());
					return dateString;

				}
				return String.valueOf(cell.getNumericCellValue());

			case STRING:
				return String.valueOf(cell.getStringCellValue());

			case BLANK:
				break;

			case ERROR:
				return String.valueOf(cell.getErrorCellValue());

			case FORMULA:
				switch (cell.getCachedFormulaResultTypeEnum()) {
				case NUMERIC:
					return String.valueOf(cell.getNumericCellValue());
				case STRING:
					return cell.getRichStringCellValue().toString();
				}
			}
		}
		return null;
	}
}
