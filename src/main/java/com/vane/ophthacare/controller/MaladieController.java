package com.vane.ophthacare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.repository.MaladieRepository;

@RestController
@CrossOrigin
@RequestMapping("/maladie")
public class MaladieController {

public static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private MaladieRepository maladieRepository;
}
