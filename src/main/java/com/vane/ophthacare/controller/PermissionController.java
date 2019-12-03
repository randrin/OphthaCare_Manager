package com.vane.ophthacare.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Permission;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.repository.PermissionRepository;
import com.vane.ophthacare.utils.Constants;
import com.vane.ophthacare.utils.Utils;

@RestController
@CrossOrigin
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private UserOperations userOperations;

	SimpleDateFormat format = new SimpleDateFormat(Constants.FORMAT_DATE);
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

	@GetMapping("/getAllPermissions")
	public ResponseEntity<Object> getAllPermissions(@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " GET -> /permission/getAllPermissions - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.PERMISSION_GET_ALL);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		List<Permission> listPermissions = permissionRepository.findAll();

		if (listPermissions == null) {
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.PERMISSION_GET_ALL);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_PERMISSIONS_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " GET -> /permission/getAllPermissions - Caller [" + caller + "]");
		return new ResponseEntity<Object>(listPermissions, HttpStatus.OK);
	}
	
	@PutMapping("/insert")
	public ResponseEntity<Object> insertPermission(@RequestBody Permission permission,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " INSERT -> /permission/insert - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		if (permission == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}

		String erreur = Utils.checkAttributeFromObject(permission, false);

		logger.info("Erreur response: " + erreur);

		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}

		// Set Model Permission to uppercase and popolate date
		permission.setNomPermission(permission.getNomPermission().toUpperCase());
		permission.setDateRegistration(format.format(GregorianCalendar.getInstance().getTime()));
		permission.setDateUpdate(format.format(GregorianCalendar.getInstance().getTime()));

		Permission p = permissionRepository.save(permission);

		if (p == null) {
			logger.error(ResponseCodes.ERROR_SET_PERMISSION_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_PERMISSION_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " INSERT -> /permission/insert - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(permission.getIdPermission()),
				permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_INSERT);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_PERMISSION), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> updatePermission(@RequestBody Permission permission,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " UPDATE -> /permission/update - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		if (permission == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}

		String erreur = Utils.checkAttributeFromObject(permission, false);

		logger.info("Erreur response: " + erreur);

		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}

		// Set Model Permission to uppercase
		permission.setNomPermission(permission.getNomPermission().toUpperCase());
		permission.setDateUpdate(format.format(GregorianCalendar.getInstance().getTime()));

		Permission p = permissionRepository.save(permission);

		if (p == null) {
			logger.error(ResponseCodes.ERROR_MODIFY_PERMISSION_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(permission.getIdPermission()),
					permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_MODIFY_PERMISSION_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " UPDATE -> /permission/update - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(permission.getIdPermission()),
				permission.getNomPermission(), caller, UserOperationsCodes.PERMISSION_REPORT_UPDATE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_MODIFY_PERMISSION), HttpStatus.OK);
	}
	
	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<Object> deletePermission(@PathVariable("id") String id,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " DELETE -> /permission/delete/{id} - Caller [" + caller + "]");

		if (id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.PERMISSION_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.PERMISSION_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		try {
			permissionRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_PERMISSION_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.PERMISSION_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_PERMISSION_DB), HttpStatus.OK);
		}
		
		Iterator<Permission> iter = permissionRepository.findAll().iterator();

		while (iter.hasNext()) {
			Permission permission = iter.next();
			if (permission.getIdPermission().equals(Integer.parseInt(id))) {
				iter.remove();
			}
		}

		logger.info(Constants.END + " DELETE -> /permisison/delete/{id} - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(id), caller,
				UserOperationsCodes.PERMISSION_REPORT_DELETE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_PERMISSION), HttpStatus.OK);
	}
}
