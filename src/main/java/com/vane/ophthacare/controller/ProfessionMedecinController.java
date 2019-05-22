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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.Exception.Response;
import com.vane.ophthacare.Exception.ResponseCodes;
import com.vane.ophthacare.model.ProfessionMedecin;
import com.vane.ophthacare.repository.ProfessionMedecinRepository;
import com.vane.ophthacare.utils.Utils;


@RestController
@CrossOrigin
@RequestMapping("/profession")
public class ProfessionMedecinController {

public static final Logger logger = LoggerFactory.getLogger(ProfessionMedecinController.class);
	
	@Autowired
	private ProfessionMedecinRepository professionMedecinRepository;
	
	public List<ProfessionMedecin> professionMedecinList = new ArrayList<>();
	
	@GetMapping("/getAllProfessionsMedecins")
	public ResponseEntity<Object> getAllProfessionsMedecins() {
		logger.info("GET -> /profession/getAllProfessionsMedecins - Start");
		
		List<ProfessionMedecin> listProfessionMedecins = professionMedecinRepository.findAll();
		
		if (listProfessionMedecins == null) {
			logger.error(ResponseCodes.ERROR_GET_PROFESSIONS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_PROFESSIONS_DB), HttpStatus.OK);
		}
		
		logger.info("GET -> /profession/getAllProfessionsMedecins - End");
		return new ResponseEntity<Object>(listProfessionMedecins, HttpStatus.OK);
	}
	
	@PutMapping("/insert")
	public ResponseEntity<Object> insertProfessionMedecin(@RequestBody ProfessionMedecin professionMedecin, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("INSERT -> /profession/insert - Start - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(professionMedecin == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(professionMedecin, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Profession Médecin
		professionMedecin.setCodeProfession(Utils.codeProfessionMedecin(professionMedecin.getNomProfession()));
		
		ProfessionMedecin pm = professionMedecinRepository.save(professionMedecin);
		
		if (pm == null) {
			logger.error(ResponseCodes.ERROR_SET_PROFESSIONS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_PROFESSIONS_DB), HttpStatus.OK);
		}
		
		logger.info("INSERT -> /profession/insert - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_PROFESSION), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Object> updateProfessionMedecin(@RequestBody ProfessionMedecin professionMedecin, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("UPDATE -> /profession/update - Start - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(professionMedecin == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(professionMedecin, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Profession Médecin
		professionMedecin.setCodeProfession(Utils.codeProfessionMedecin(professionMedecin.getNomProfession()));
				
		ProfessionMedecin pm = professionMedecinRepository.save(professionMedecin);
		
		if (pm == null) {
			logger.error(ResponseCodes.ERROR_SET_PROFESSIONS_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_PROFESSIONS_DB), HttpStatus.OK);
		}
		
		logger.info("UPDATE -> /profession/update - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_MODIFY_PROFESSION), HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/delete/{id}"})
	public ResponseEntity<Object> deleteProfessionMedecin (@PathVariable("id") String id,
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("DELETE -> /profession/delete/{id} - Start - Caller ["+caller+"]");

		if(id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}
				
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		try {
			professionMedecinRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_PROFESSION_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_PROFESSION_DB), HttpStatus.OK);
		}
		
		Iterator<ProfessionMedecin> iter = professionMedecinList.iterator();

		while (iter.hasNext()) {
			ProfessionMedecin medecinList = iter.next();
			if(medecinList.getIdProfession().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info("DELETE -> /profession/delete/{id} - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_PROFESSION), HttpStatus.OK);
	}
}
