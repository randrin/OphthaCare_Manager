package com.vane.ophthacare.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.repository.AdministrateurRepository;
import com.vane.ophthacare.utils.PDFGenerator;

@RestController
@CrossOrigin
@RequestMapping("/pdf")
public class PdfController {

	private static final Logger logger = LoggerFactory.getLogger(PdfController.class);
	
	@Autowired
    AdministrateurRepository administrateurRepository;
 
	@GetMapping(value = "/admins", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customersReport() throws IOException {
		List<Administrateur> admins = administrateurRepository.findAll();
		String tableTitle = "Administrateurs Table";
		Integer columnNumber = 5;
		String[] headerTable = {"ID", "Nom", "Prenom", "Pseudo", "Role"};
		ByteArrayInputStream bis = PDFGenerator.PDFReport(admins, tableTitle, columnNumber, headerTable);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=Administrateurs.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
