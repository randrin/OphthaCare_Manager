package com.vane.ophthacare.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.Exception.Response;
import com.vane.ophthacare.Exception.ResponseCodes;
import com.vane.ophthacare.excel.export.ExcelBuilder;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.repository.PatientRepository;

@RestController
@CrossOrigin
@RequestMapping("/excel")
@Controller
public class ExcelController {

	@Autowired
	public PatientRepository patientRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);
	
	@GetMapping(value="/downloadExcelPatients", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelUtenze(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("DOWNLOAD -> /excel/downloadExcelPatients - Start - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<Patient> patientList = patientRepository.findAll();

		if(patientList != null && patientList.size() > 0) {
			workbook =  ExcelBuilder.buildExcelPatient(patientList);
		} 

		if(workbook == null) {
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_EXCEL_CONCURRENCY_EXPORT), HttpStatus.OK);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		ByteArrayInputStream test = new ByteArrayInputStream(out.toByteArray());


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=export.xlsx");

		logger.info("DOWNLOAD -> /excel/downloadExcelPatients - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
	
}
