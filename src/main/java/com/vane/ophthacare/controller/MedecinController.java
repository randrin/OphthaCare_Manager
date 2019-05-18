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
import com.vane.ophthacare.model.Medecin;
import com.vane.ophthacare.repository.MedecinRepository;

@RestController
@CrossOrigin
@RequestMapping("/medecin")
public class MedecinController {

	public static final Logger logger = LoggerFactory.getLogger(MedecinController.class);
	
	@Autowired
	private MedecinRepository medecinRepository;
	
	public List<Medecin> specialisteList = new ArrayList<>();
	
	@GetMapping("/getAllMedecins")
	public ResponseEntity<Object> getAllMedecins () {
		logger.info("GET -> /medecin/getAllMedecins - Start");
		
		List<Medecin> listMedecins = medecinRepository.findAll();
		
		if (listMedecins == null) {
			logger.error(ResponseCodes.ERROR_GET_MEDECINS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_MEDECINS_DB), HttpStatus.OK);
		}
		
		logger.info("GET -> /medecin/getAllMedecins - End");
		return new ResponseEntity<Object>(listMedecins, HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/delete/{id}"})
	public ResponseEntity<Object> deleteSpecialiste (@PathVariable("id") String id,
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("DELETE -> /medecin/delete/{id} - Start - Caller ["+caller+"]");
		
		if(id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}
				
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		try {
			medecinRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_MEDECIN_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_MEDECIN_DB), HttpStatus.OK);
		}
		
		Iterator<Medecin> iter = specialisteList.iterator();

		while (iter.hasNext()) {
			Medecin medecinList = iter.next();
			if(medecinList.getIdMedecin().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info("DELETE -> /medecin/delete/{id} - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_MEDECIN), HttpStatus.OK);
	}
	
}
