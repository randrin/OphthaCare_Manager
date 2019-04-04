package com.vane.ophthacare.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.poi.util.StringUtil;
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

import com.vane.ophthacare.Exception.Response;
import com.vane.ophthacare.Exception.ResponseCodes;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.repository.PatientRepository;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {

	public static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private PatientRepository patientRepository;
	
	public List<Patient> patientList = new ArrayList<>();
	
	@GetMapping("/getAll")
	public List<Patient> getAllPatients () {
		logger.info("Start method getAllPatients");
		return patientRepository.findAll();
	}
	
	@DeleteMapping(path ={"/delete/{id}"})
	public ResponseEntity<Object> deletePatient (@PathVariable("id") String id,
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("DELETE -> /patient/delete/{id} - Start - Caller ["+caller+"]");
		
		if(id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}
				
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		try {
			patientRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_USER_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_USER_DB), HttpStatus.OK);
		}
		
		Iterator<Patient> iter = patientList.iterator();

		while (iter.hasNext()) {
			Patient patient = iter.next();
			if(patient.getIdPatient().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info("DELETE patient/delete/{id} - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_USER), HttpStatus.OK);
	}
} 
