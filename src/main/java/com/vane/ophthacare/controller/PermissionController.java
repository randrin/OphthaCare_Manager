package com.vane.ophthacare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@CrossOrigin
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private UserOperations userOperations;
	
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
}
