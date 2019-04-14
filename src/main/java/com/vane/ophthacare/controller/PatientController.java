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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.Exception.Response;
import com.vane.ophthacare.Exception.ResponseCodes;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.repository.PatientRepository;
import com.vane.ophthacare.utils.Utils;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {

	public static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private PatientRepository patientRepository;
	
	public List<Patient> patientList = new ArrayList<>();
	
	@GetMapping("/getAllPatients")
	public ResponseEntity<Object> getAllPatients () {
		logger.info("GET -> /patient/getAllPatients - Start");
		
		List<Patient> listPatients = patientRepository.findAll();
		
		if (listPatients == null) {
			logger.error(ResponseCodes.ERROR_GET_PATIENTS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_PATIENTS_DB), HttpStatus.OK);
		}
		
		logger.info("GET -> /patient/getAllPatients - End");
		return new ResponseEntity<Object>(listPatients, HttpStatus.OK);
	}
	
	@PutMapping("/insert")
	public ResponseEntity<Object> insertPatient(@RequestBody Patient patient, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("INSERT -> /patient/insert - Start - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(patient == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(patient, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Patient
		patient.setCodePatient(Utils.codePatient(patient.getNomPatient(), patient.getPrenomPatient(), patient.getDateNaisPatient()));
		patient.setAgePatient(Utils.calculAgePatient(patient.getDateNaisPatient()));
		
		Patient p = patientRepository.save(patient);
		
		if (p == null) {
			logger.error(ResponseCodes.ERROR_SET_PATIENT_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_PATIENT_DB), HttpStatus.OK);
		}
		
		logger.info("INSERT -> patient/insert - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_PATIENT), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Object> updatePatient(@RequestBody Patient patient, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("UPDATE -> /patient/update - Start - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(patient == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(patient, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Patient
		patient.setAgePatient(Utils.calculAgePatient(patient.getDateNaisPatient()));
		
		Patient p = patientRepository.save(patient);
		
		if (p == null) {
			logger.error(ResponseCodes.ERROR_SET_PATIENT_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_PATIENT_DB), HttpStatus.OK);
		}
		
		logger.info("UPDATE -> patient/update - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_MODIFY_PATIENT), HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/delete/{id}"})
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
			logger.error(ResponseCodes.ERROR_DELETE_PATIENT_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_PATIENT_DB), HttpStatus.OK);
		}
		
		Iterator<Patient> iter = patientList.iterator();

		while (iter.hasNext()) {
			Patient patient = iter.next();
			if(patient.getIdPatient().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info("DELETE -> patient/delete/{id} - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_PATIENT), HttpStatus.OK);
	}
} 
