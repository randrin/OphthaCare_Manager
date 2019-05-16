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
import com.vane.ophthacare.model.Maladie;
import com.vane.ophthacare.repository.MaladieRepository;

@RestController
@CrossOrigin
@RequestMapping("/maladie")
public class MaladieController {

public static final Logger logger = LoggerFactory.getLogger(MaladieController.class);
	
	@Autowired
	private MaladieRepository maladieRepository;
	
	@GetMapping("/getAllMaladies")
	public ResponseEntity<Object> getAllMaladies () {
		logger.info("GET -> /maladie/getAllMaladies - Start");
		
		List<Maladie> listMaladies = maladieRepository.findAll();
		
		if (listMaladies == null) {
			logger.error(ResponseCodes.ERROR_GET_DISEASES_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_DISEASES_DB), HttpStatus.OK);
		}
		
		logger.info("GET -> /maladie/getAllMaladies - End");
		return new ResponseEntity<Object>(listMaladies, HttpStatus.OK);
	}
}
