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
import com.vane.ophthacare.model.Maladie;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.repository.MaladieRepository;
import com.vane.ophthacare.utils.Constants;
import com.vane.ophthacare.utils.Utils;

@RestController
@CrossOrigin
@RequestMapping("/maladie")
public class MaladieController {

	public static final Logger logger = LoggerFactory.getLogger(MaladieController.class);

	@Autowired
	private MaladieRepository maladieRepository;

	@Autowired
	private UserOperations userOperations;

	public List<Maladie> maladieList = new ArrayList<>();

	@GetMapping("/getAllMaladies")
	public ResponseEntity<Object> getAllMaladies(@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " GET -> /maladie/getAllMaladies - Caller [" + caller + "]");

		List<Maladie> listMaladies = maladieRepository.findAll();

		if (listMaladies == null) {
			logger.error(ResponseCodes.ERROR_GET_DISEASES_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.DISEASE_GET_ALL);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_DISEASES_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " GET -> /maladie/getAllMaladies - Caller [" + caller + "]");
		return new ResponseEntity<Object>(listMaladies, HttpStatus.OK);
	}

	@PutMapping("/insert")
	public ResponseEntity<Object> insertMaladie(@RequestBody Maladie maladie,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " INSERT -> /maladie/insert - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		if (maladie == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}

		String erreur = Utils.checkAttributeFromObject(maladie, false);

		logger.info("Erreur response: " + erreur);

		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}

		// Set Model Maladie
		maladie.setCodeMaladie(Utils.codeMaladie(maladie.getNomMaladie()));

		Maladie m = maladieRepository.save(maladie);

		if (m == null) {
			logger.error(ResponseCodes.ERROR_SET_DISEASE_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_DISEASE_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " INSERT -> /maladie/insert - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(maladie.getIdMaladie()),
				maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_INSERT);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_DISEASE), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Object> updateMaladie(@RequestBody Maladie maladie,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " UPDATE -> /maladie/update - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		if (maladie == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}

		String erreur = Utils.checkAttributeFromObject(maladie, false);

		logger.info("Erreur response: " + erreur);

		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}

		// Set Model Maladie
		maladie.setCodeMaladie(Utils.codeMaladie(maladie.getNomMaladie()));

		Maladie m = maladieRepository.save(maladie);

		if (m == null) {
			logger.error(ResponseCodes.ERROR_MODIFY_DISEASE_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(maladie.getIdMaladie()),
					maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_MODIFY_DISEASE_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " UPDATE -> /maladie/update - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(maladie.getIdMaladie()),
				maladie.getNomMaladie(), caller, UserOperationsCodes.DISEASE_REPORT_UPDATE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_MODIFY_DISEASE), HttpStatus.OK);
	}

	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<Object> deleteMaladie(@PathVariable("id") String id,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " DELETE -> /maladie/delete/{id} - Caller [" + caller + "]");

		if (id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.DISEASE_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.DISEASE_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		try {
			maladieRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_DISEASE_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.DISEASE_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_DISEASE_DB), HttpStatus.OK);
		}

		Iterator<Maladie> iter = maladieList.iterator();

		while (iter.hasNext()) {
			Maladie maladie = iter.next();
			if (maladie.getIdMaladie().equals(Integer.parseInt(id))) {
				iter.remove();
			}
		}

		logger.info(Constants.END + " DELETE -> /maladie/delete/{id} - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(id), caller,
				UserOperationsCodes.DISEASE_REPORT_DELETE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_DISEASE), HttpStatus.OK);
	}
}
