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

import com.vane.ophthacare.Exception.Response;
import com.vane.ophthacare.Exception.ResponseCodes;
import com.vane.ophthacare.model.Specialiste;
import com.vane.ophthacare.model.Patient;
import com.vane.ophthacare.repository.SpecialisteRepository;

@RestController
@CrossOrigin
@RequestMapping("/specialiste")
public class SpecialisteController {

	public static final Logger logger = LoggerFactory.getLogger(SpecialisteController.class);
	
	@Autowired
	private SpecialisteRepository medecinRepository;
	
	@GetMapping("/getAllSpecialistes")
	public ResponseEntity<Object> getAllSpecialistes () {
		logger.info("GET -> /specialiste/getAllSpecialistes - Start");
		
		List<Specialiste> listMedecins = medecinRepository.findAll();
		
		if (listMedecins == null) {
			logger.error(ResponseCodes.ERROR_GET_SPECIALISTES_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_SPECIALISTES_DB), HttpStatus.OK);
		}
		
		logger.info("GET -> /specialiste/getAllSpecialistes - End");
		return new ResponseEntity<Object>(listMedecins, HttpStatus.OK);
	}
	
}
