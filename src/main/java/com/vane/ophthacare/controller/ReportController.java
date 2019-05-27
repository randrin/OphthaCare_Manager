package com.vane.ophthacare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Report;
import com.vane.ophthacare.repository.ReportRepository;

@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportRepository reportRepository;
	
	public static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@GetMapping("/getAllReports")
	public ResponseEntity<Object> getAllReports() {
		logger.info("GET -> /report/getAllReports - Start");
		
		List<Report> listReport = reportRepository.findAll();
		
		if (listReport == null) {
			logger.error(ResponseCodes.ERROR_GET_REPORTS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_REPORTS_DB), HttpStatus.OK);
		}
		
		logger.info("GET -> /report/getAllReports - End");
		return new ResponseEntity<Object>(listReport, HttpStatus.OK);
	}
}
