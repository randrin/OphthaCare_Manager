package com.vane.ophthacare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.Exception.ExceptionCodes;
import com.vane.ophthacare.Exception.Response;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.repository.AdministrateurRepository;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdministrateurController {

	@Autowired
	private AdministrateurRepository administrateurRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(Administrateur.class);
	
	@GetMapping("/login")
	public ResponseEntity<Object> login (
			@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		
		logger.info("Start logging administrateur");
		
		Administrateur admin = administrateurRepository.findByPseudoAdmin(username);
		
		if (admin == null) {
			return new ResponseEntity<Object>(new Response(ExceptionCodes.ERROR_ADMIN_NO_PERMISSION), HttpStatus.OK);
		} else {
			if (admin.isActive()) {
				return new ResponseEntity<Object>(admin, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new Response(ExceptionCodes.ERROR_ADMIN_NO_PERMISSION), HttpStatus.OK);
			}
		}
	}
}
