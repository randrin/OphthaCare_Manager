package com.vane.ophthacare.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	private static final Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static <T> ByteArrayInputStream PDFReport(List<T> listObject, String tableTitle, Integer columnNumber,
			String[] headerTable) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph(tableTitle, font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(columnNumber);

			// Add PDF Table Header ->
			Stream.of(headerTable).forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (Object obj : listObject) {
				for (int i = 0; i < headerTable.length; i++) {
					PdfPCell idCell = new PdfPCell(new Phrase(headerTable[i]));
					idCell.setPaddingLeft(4);
					idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(idCell);
				}
			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			logger.info("Error cause: " + e.getCause() + " - Error Message: " + e.getMessage());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}