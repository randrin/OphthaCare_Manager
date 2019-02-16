package com.vane.ophthacare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.repository.PatientRepository;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {

	public static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/getAll")
	public List<Patient> getAllPatients () {
		logger.info("Start method getAllPatients");
		return patientRepository.findAll();
	}
}
