package com.vane.ophthacare.controller;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.model.Report;
import com.vane.ophthacare.repository.ReportRepository;
import com.vane.ophthacare.utils.Constants;

@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportRepository reportRepository;

	public static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@GetMapping("/getAllReports")
	public ResponseEntity<Object> getAllReports(@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " GET -> /report/getAllReports - Caller [" + caller + "]");

		List<Report> listReport = reportRepository.findAll();

		if (listReport == null) {
			logger.error(ResponseCodes.ERROR_GET_REPORTS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_REPORTS_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " GET -> /report/getAllReports - Caller [" + caller + "]");
		return new ResponseEntity<Object>(listReport, HttpStatus.OK);
	}

	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<Object> deleteReport(@PathVariable("id") String id,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " DELETE -> /report/delete/{id} - Caller [" + caller + "]");

		if (id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		try {
			reportRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_REPORT_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_REPORT_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " DELETE -> /report/delete/{id} - Caller [" + caller + "]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_REPORT), HttpStatus.OK);
	}
}
