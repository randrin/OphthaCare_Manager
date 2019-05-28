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

import com.vane.ophthacare.excel.export.ExcelBuilder;
import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.Maladie;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.model.ProfessionMedecin;
import com.vane.ophthacare.model.Report;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.model.Medecin;
import com.vane.ophthacare.repository.AdministrateurRepository;
import com.vane.ophthacare.repository.MaladieRepository;
import com.vane.ophthacare.repository.PatientRepository;
import com.vane.ophthacare.repository.ProfessionMedecinRepository;
import com.vane.ophthacare.repository.ReportRepository;
import com.vane.ophthacare.utils.Constants;
import com.vane.ophthacare.repository.MedecinRepository;

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
	public MedecinRepository medecinRepository;
	
	@Autowired
	public ProfessionMedecinRepository professionMedecinRepository;
	
	@Autowired
	public ReportRepository reportRepository;
	
	@Autowired
	private UserOperations userOperations;
	
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
		
		logger.info(Constants.BEGIN +" GET -> /excel/downloadExcelAdministrateurs - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<Administrateur> adminList = administrateurRepository.findAll();

		if(adminList != null && adminList.size() > 0) {
			workbook =  ExcelBuilder.buildExcelAdmin(adminList);
		} 

		if(workbook == null) {
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.ERROR_EXCEL_EXPORT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_EXCEL_CONCURRENCY_EXPORT), HttpStatus.OK);
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		ByteArrayInputStream test = new ByteArrayInputStream(out.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=export.xlsx");

		logger.info(Constants.END +" GET -> /excel/downloadExcelAdministrateurs - Caller ["+caller+"]");
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
	
	@GetMapping(value="/downloadExcelMedecins", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelMedecins(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("GET -> /excel/downloadExcelMedecins - Start - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<Medecin> medecinList = medecinRepository.findAll();

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

		logger.info("GET -> /excel/downloadExcelMedecins - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
	
	@GetMapping(value="/downloadExcelProfessionsMedecins", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelProfessionsMedecins(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("GET -> /excel/downloadExcelProfessionsMedecins - Start - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<ProfessionMedecin> professionMedecinList = professionMedecinRepository.findAll();

		if(professionMedecinList != null && professionMedecinList.size() > 0) {
			workbook =  ExcelBuilder.buildExcelProfessionMedecin(professionMedecinList);
		} 

		if(workbook == null) {
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_EXCEL_CONCURRENCY_EXPORT), HttpStatus.OK);
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		ByteArrayInputStream test = new ByteArrayInputStream(out.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=export.xlsx");

		logger.info("GET -> /excel/downloadExcelProfessionsMedecins - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
	
	@GetMapping(value="/downloadExcelReports", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> downloadExcelReports(@RequestHeader(value = "caller", required = false) String caller) throws IOException {
		
		logger.info("GET -> /excel/downloadExcelRports - Start - Caller ["+caller+"]");
		
		Workbook workbook = null;
		List<Report> reportList = reportRepository.findAll();

		if(reportList != null && reportList.size() > 0) {
			workbook =  ExcelBuilder.buildExcelReport(reportList);
		} 

		if(workbook == null) {
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_EXCEL_CONCURRENCY_EXPORT), HttpStatus.OK);
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		ByteArrayInputStream test = new ByteArrayInputStream(out.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=export.xlsx");

		logger.info("GET -> /excel/downloadExcelReports - End - Caller ["+caller+"]");
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(test));
	}
}
