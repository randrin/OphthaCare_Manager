package com.vane.ophthacare.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.repository.AdministrateurRepository;
import com.vane.ophthacare.utils.Constants;
import com.vane.ophthacare.utils.PDFGenerator;

@RestController
@CrossOrigin
@RequestMapping("/pdf")
public class PdfController {

	private static final Logger logger = LoggerFactory.getLogger(PdfController.class);

	@Autowired
	AdministrateurRepository administrateurRepository;

	@Autowired
	private UserOperations userOperations;

	@GetMapping(value = "/admins", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getPDFAdmins(
			@RequestHeader(value = "caller", required = false) String caller) throws IOException {

		logger.info(Constants.BEGIN + " GET -> /pdf/getPDFAdmins - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.ADMIN_DOWNLOAD_PDF);
			return new ResponseEntity<InputStreamResource>(HttpStatus.OK);
		}

		List<Administrateur> admins = administrateurRepository.findAll();
		String tableTitle = "Administrateurs Table";
		Integer columnNumber = 5;
		String[] headerTable = { "ID", "Nom", "Prenom", "Pseudo", "Role" };
		ByteArrayInputStream bis = PDFGenerator.PDFReport(admins, tableTitle, columnNumber, headerTable);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=Administrateurs.pdf");

		userOperations.saveOperationReport(Constants.SUCCESS, caller, caller, UserOperationsCodes.ADMIN_DOWNLOAD_PDF);
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
