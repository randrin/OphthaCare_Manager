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
import com.vane.ophthacare.model.Maladie;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.model.Specialiste;
import com.vane.ophthacare.repository.AdministrateurRepository;
import com.vane.ophthacare.repository.MaladieRepository;
import com.vane.ophthacare.repository.PatientRepository;
import com.vane.ophthacare.repository.SpecialisteRepository;

@RestController
@CrossOrigin
@RequestMapping("/excel")
@Controller
public class ExcelController {

	@Autowired
	public PatientRepository patientRepository;
	
	@Autowired
	public AdministrateurRepository administrateurRepository;
	
	@Autowired
	public MaladieRepository maladieRepository;
	
	@Autowired
	public SpecialisteRepository specialisteRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);
	
	@GetMapping(value="/downloadExcelPatients", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelPatients(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("GET -> /excel/downloadExcelPatients - Start - Caller ["+caller+"]");
		
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

		logger.info("GET -> /excel/downloadExcelPatients - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
	
	@GetMapping(value="/downloadExcelAdministrateurs", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelAdministrateurs(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("GET -> /excel/downloadExcelAdministrateurs - Start - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<Administrateur> adminList = administrateurRepository.findAll();

		if(adminList != null && adminList.size() > 0) {
			workbook =  ExcelBuilder.buildExcelAdmin(adminList);
		} 

		if(workbook == null) {
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_EXCEL_CONCURRENCY_EXPORT), HttpStatus.OK);
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		ByteArrayInputStream test = new ByteArrayInputStream(out.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=export.xlsx");

		logger.info("GET -> /excel/downloadExcelAdministrateurs - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
	
	@GetMapping(value="/downloadExcelMaladies", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelMaladies(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("GET -> /excel/downloadExcelMaladies - Start - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<Maladie> maladieList = maladieRepository.findAll();

		if(maladieList != null && maladieList.size() > 0) {
			workbook =  ExcelBuilder.buildExcelMaladie(maladieList);
		} 

		if(workbook == null) {
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_EXCEL_CONCURRENCY_EXPORT), HttpStatus.OK);
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		ByteArrayInputStream test = new ByteArrayInputStream(out.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=export.xlsx");

		logger.info("GET -> /excel/downloadExcelMaladies - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
	
	@GetMapping(value="/downloadExcelSpecialistes", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelSpecialistes(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("GET -> /excel/downloadExcelSpecialistes - Start - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<Specialiste> medecinList = specialisteRepository.findAll();

		if(medecinList != null && medecinList.size() > 0) {
			workbook =  ExcelBuilder.buildExcelMedecin(medecinList);
		} 

		if(workbook == null) {
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_EXCEL_CONCURRENCY_EXPORT), HttpStatus.OK);
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		ByteArrayInputStream test = new ByteArrayInputStream(out.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=export.xlsx");

		logger.info("GET -> /excel/downloadExcelSpecialistes - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
}
