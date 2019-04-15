package com.vane.ophthacare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AdministrateurController.class);
	
	@GetMapping("/getAllAdmins")
	public ResponseEntity<Object> getAllAdmins() {
		
		logger.info("GET -> /admin/getAllAdmins - Start");
		
		List<Administrateur> listAdmins = administrateurRepository.findAll();
		
		if (listAdmins == null) {
			return new ResponseEntity<Object>(new Response(ExceptionCodes.ERROR_ADMIN_NO_PERMISSION), HttpStatus.OK);
		}
		
		logger.info("GET -> /admin/getAllAdmins - End");
		return new ResponseEntity<Object>(listAdmins, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login (
			@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		
		logger.info("POST -> /admin/login - Start");
		logger.info("Start logging administrateur");
		logger.info("Username/Pseudo administrateur: " +username);
		logger.info("Password administrateur: " +password);
		
		Administrateur admin = administrateurRepository.findByPseudoAdmin(username);
		//logger.info("Admin qui est entrain de se connecter: " +admin.getNomAdmin()+ " "+admin.getPrenomAdmin());
		
		if (admin == null) {
			return new ResponseEntity<Object>(new Response(ExceptionCodes.ERROR_ADMIN_NO_PERMISSION), HttpStatus.OK);
		} else {
			if (admin.getActiveAdmin().equals("true")) {
				logger.info("Admin : [" +admin.getNomAdmin()+ " "+admin.getPrenomAdmin()+ "] is activated? " +admin.getActiveAdmin());
				logger.info("POST -> /admin/login - End");
				return new ResponseEntity<Object>(admin, HttpStatus.OK);
			} else {
				logger.info("POST -> /admin/login - End");
				return new ResponseEntity<Object>(new Response(ExceptionCodes.ERROR_ADMIN_NO_PERMISSION), HttpStatus.OK);
			}
		}
	}
}
