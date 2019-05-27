package com.vane.ophthacare.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.exception.ExceptionCodes;
import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.repository.AdministrateurRepository;
import com.vane.ophthacare.utils.Constants;
import com.vane.ophthacare.utils.Utils;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdministrateurController {

	@Autowired
	private AdministrateurRepository administrateurRepository;
	
	@Autowired
	private UserOperations userOperations;
	
	public List<Administrateur> adminList = new ArrayList<>();
	
	SimpleDateFormat format = new SimpleDateFormat(Constants.FORMAT_DATETIME);
	
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
	
	@PutMapping("/insert")
	public ResponseEntity<Object> insertAdmin(@RequestBody Administrateur admin, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("INSERT -> /admin/insert - Start - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(admin == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(admin, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		//Set Model Admin
		admin.setRegistrationAdmin(format.format(GregorianCalendar.getInstance().getTime()));
		admin.setLastLoginAdmin(format.format(GregorianCalendar.getInstance().getTime()));
		
		Administrateur a = administrateurRepository.save(admin);
		
		if (a == null) {
			logger.error(ResponseCodes.ERROR_SET_ADMIN_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_ADMIN_DB), HttpStatus.OK);
		}
		
		logger.info("INSERT -> /admin/insert - End - Caller ["+caller+"]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(admin.getIdAdmin()), admin.getNomAdmin(), caller, UserOperationsCodes.ADMIN_REPORT_INSERT);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_ADMIN), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> updateAdmin(@RequestBody Administrateur admin, 
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("UPDATE -> /admin/update - Start - Caller ["+caller+"]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if(admin == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(admin, false);
		
		logger.info("Erreur response: " +erreur);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		Administrateur a = administrateurRepository.save(admin);
		
		if (a == null) {
			logger.error(ResponseCodes.ERROR_SET_ADMIN_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_ADMIN_DB), HttpStatus.OK);
		}
		
		logger.info("UPDATE -> /admin/update - End - Caller ["+caller+"]");
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_MODIFY_ADMIN), HttpStatus.OK);
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
	@DeleteMapping(path = {"/delete/{id}"})
	public ResponseEntity<Object> deleteAdministrateur (@PathVariable("id") String id,
			@RequestHeader(value= "caller",required = false) String caller) {
		
		logger.info("DELETE -> /admin/delete/{id} - Start - Caller ["+caller+"]");
		
		if(id == null) { 
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}
				
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		try {
			administrateurRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_ADMIN_DB.toString());
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_ADMIN_DB), HttpStatus.OK);
		}
		
		Iterator<Administrateur> iter = adminList.iterator();

		while (iter.hasNext()) {
			Administrateur admin = iter.next();
			if(admin.getIdAdmin().equals(Integer.parseInt(id))) {	
				iter.remove();
			}
		}
		
		logger.info("DELETE -> admin/delete/{id} - End - Caller ["+caller+"]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(id), caller, UserOperationsCodes.ADMIN_REPORT_DELETE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_ADMIN), HttpStatus.OK);
	}
}
