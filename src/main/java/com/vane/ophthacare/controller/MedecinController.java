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

import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Medecin;
import com.vane.ophthacare.model.ProfessionMedecin;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.repository.MedecinRepository;
import com.vane.ophthacare.repository.ProfessionMedecinRepository;
import com.vane.ophthacare.utils.Constants;
import com.vane.ophthacare.utils.Utils;

@RestController
@CrossOrigin
@RequestMapping("/medecin")
public class MedecinController {

	public static final Logger logger = LoggerFactory.getLogger(MedecinController.class);
	
	@Autowired
	private MedecinRepository medecinRepository;
	
	@Autowired
	private ProfessionMedecinRepository professionMedecinRepository;
	
	@Autowired
	private UserOperations userOperations;
	
	public List<Medecin> specialisteList = new ArrayList<>();
	public List<ProfessionMedecin> professionMedecinList = new ArrayList<>();
	
	@GetMapping("/getAllMedecins")
	public ResponseEntity<Object> getAllMedecins (@RequestHeader(value= "caller",required = false) String caller) {
		logger.info(Constants.BEGIN +" GET -> /medecin/getAllMedecins - Caller ["+caller+"]");
		
		List<Medecin> listMedecins = medecinRepository.findAll();
		professionMedecinList = professionMedecinRepository.findAll();
		
		if (listMedecins == null) {
			logger.error(ResponseCodes.ERROR_GET_MEDECINS_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.MEDECIN_GET_ALL);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_MEDECINS_DB), HttpStatus.OK);
		}
		
		logger.info(Constants.END +" GET -> /medecin/getAllMedecins - Caller ["+caller+"]");
		return new ResponseEntity<Object>(listMedecins, HttpStatus.OK);
	}
	
	@PutMapping("/insert")
	public ResponseEntity<Object> insertMedecin(@RequestBody Medecin medecin, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info(Constants.BEGIN +" INSERT -> /medecin/insert - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(medecin == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(medecin, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Medecin
		medecin.setMatriculeMedecin(Utils.matriculeMedecin(professionMedecinList, medecin.getProfessionMedecin(), medecin.getNomMedecin(), medecin.getPrenomMedecin(), medecin.getDateNaisMedecin()));
		medecin.setAgeMedecin(Utils.calculAgePatient(medecin.getDateNaisMedecin()));
		
		Medecin m = medecinRepository.save(medecin);
		
		if (m == null) {
			logger.error(ResponseCodes.ERROR_SET_MEDECINS_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_MEDECINS_DB), HttpStatus.OK);
		}
		
		logger.info(Constants.END +" INSERT -> /medecin/insert - Caller ["+caller+"]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_INSERT);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_MEDECIN), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Object> updateMedecin(@RequestBody Medecin medecin, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info(Constants.BEGIN +" UPDATE -> /medecin/update - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(medecin == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(medecin, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Medecin
		medecin.setAgeMedecin(Utils.calculAgePatient(medecin.getDateNaisMedecin()));
		
		Medecin m = medecinRepository.save(medecin);
		
		if (m == null) {
			logger.error(ResponseCodes.ERROR_SET_MEDECINS_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_MEDECINS_DB), HttpStatus.OK);
		}
		
		logger.info(Constants.END +" UPDATE -> /medecin/update - Caller ["+caller+"]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(medecin.getIdMedecin()), medecin.getNomMedecin(), caller, UserOperationsCodes.MEDECIN_REPORT_UPDATE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_MODIFY_MEDECIN), HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/delete/{id}"})
	public ResponseEntity<Object> deleteMedecin (@PathVariable("id") String id,
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info(Constants.BEGIN +" DELETE -> /medecin/delete/{id} - Caller ["+caller+"]");
		
		if(id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller, UserOperationsCodes.MEDECIN_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}
				
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller, UserOperationsCodes.MEDECIN_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		try {
			medecinRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_MEDECIN_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller, UserOperationsCodes.MEDECIN_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_MEDECIN_DB), HttpStatus.OK);
		}
		
		Iterator<Medecin> iter = specialisteList.iterator();

		while (iter.hasNext()) {
			Medecin medecinList = iter.next();
			if(medecinList.getIdMedecin().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info(Constants.END +" DELETE -> /medecin/delete/{id} - Caller ["+caller+"]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(id), caller, UserOperationsCodes.MEDECIN_REPORT_DELETE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_MEDECIN), HttpStatus.OK);
	}
	
}
