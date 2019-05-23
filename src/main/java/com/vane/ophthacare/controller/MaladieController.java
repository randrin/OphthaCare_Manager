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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.Exception.Response;
import com.vane.ophthacare.Exception.ResponseCodes;
import com.vane.ophthacare.model.Maladie;
import com.vane.ophthacare.model.Medecin;
import com.vane.ophthacare.repository.MaladieRepository;
import com.vane.ophthacare.utils.Utils;

@RestController
@CrossOrigin
@RequestMapping("/maladie")
public class MaladieController {

public static final Logger logger = LoggerFactory.getLogger(MaladieController.class);
	
	@Autowired
	private MaladieRepository maladieRepository;
	
	public List<Maladie> maladieList = new ArrayList<>();
	
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
	
	@PutMapping("/insert")
	public ResponseEntity<Object> insertMaladie(@RequestBody Maladie maladie, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("INSERT -> /maladie/insert - Start - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(maladie == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(maladie, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Maladie
		maladie.setCodeMaladie(Utils.codeMaladie(maladie.getNomMaladie()));
		
		Maladie m = maladieRepository.save(maladie);
		
		if (m == null) {
			logger.error(ResponseCodes.ERROR_SET_DISEASE_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_DISEASE_DB), HttpStatus.OK);
		}
		
		logger.info("INSERT -> /maladie/insert - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_DISEASE), HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/delete/{id}"})
	public ResponseEntity<Object> deleteSpecialiste (@PathVariable("id") String id,
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("DELETE -> /maladie/delete/{id} - Start - Caller ["+caller+"]");
		
		if(id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}
				
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		try {
			maladieRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_DISEASE_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_DISEASE_DB), HttpStatus.OK);
		}
		
		Iterator<Maladie> iter = maladieList.iterator();

		while (iter.hasNext()) {
			Maladie maladie = iter.next();
			if(maladie.getIdMaladie().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info("DELETE -> /maladie/delete/{id} - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_DISEASE), HttpStatus.OK);
	}
}
