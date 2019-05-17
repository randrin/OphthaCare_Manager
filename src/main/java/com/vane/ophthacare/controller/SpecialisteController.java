package com.vane.ophthacare.controller;

import java.util.ArrayList;
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
	private SpecialisteRepository specialisteRepository;
	
	public List<Specialiste> specialisteList = new ArrayList<>();
	
	@GetMapping("/getAllSpecialistes")
	public ResponseEntity<Object> getAllSpecialistes () {
		logger.info("GET -> /specialiste/getAllSpecialistes - Start");
		
		List<Specialiste> listMedecins = specialisteRepository.findAll();
		
		if (listMedecins == null) {
			logger.error(ResponseCodes.ERROR_GET_SPECIALISTS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_SPECIALISTS_DB), HttpStatus.OK);
		}
		
		logger.info("GET -> /specialiste/getAllSpecialistes - End");
		return new ResponseEntity<Object>(listMedecins, HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/delete/{id}"})
	public ResponseEntity<Object> deleteSpecialiste (@PathVariable("id") String id,
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("DELETE -> /specialiste/delete/{id} - Start - Caller ["+caller+"]");
		
		if(id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}
				
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		try {
			specialisteRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_SPECIALIST_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_SPECIALIST_DB), HttpStatus.OK);
		}
		
		Iterator<Specialiste> iter = specialisteList.iterator();

		while (iter.hasNext()) {
			Specialiste specialist = iter.next();
			if(specialist.getIdMedecin().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info("DELETE -> /specialiste/delete/{id} - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_SPECIALIST), HttpStatus.OK);
	}
	
}
