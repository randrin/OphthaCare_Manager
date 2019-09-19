package com.vane.ophthacare.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.ProfileImage;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.repository.AdministrateurRepository;
import com.vane.ophthacare.repository.ProfileImageRepository;
import com.vane.ophthacare.utils.Constants;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileImageController {

	private static final Logger logger = LoggerFactory.getLogger(ProfileImageController.class);

	@Autowired
	private UserOperations userOperations;
	
	@Autowired
	private ProfileImageRepository profileImageRepository;

	@Autowired
	private AdministrateurRepository administrateurRepository;

	public Administrateur admin;
	
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadProfileImage(@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " GET -> /profile/downloadProfileImage - Caller [" + caller + "]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.GET_PROFILE);
			return new ResponseEntity<byte[]>(HttpStatus.OK);
		}
		
		// First find admin by username or pseudo
		admin = administrateurRepository.findByPseudoAdmin(caller);
		Optional<ProfileImage> fileOptional = profileImageRepository.findById(admin.getIdAdmin());

		if (fileOptional.isPresent()) {
			ProfileImage image = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
					.body(image.getPicture());
		}
		
		logger.info(Constants.END + " UPLOAD -> /profile/downloadProfileImage - Caller [" + caller + "]");
		return ResponseEntity.status(404).body(null);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<byte[]> uploadProfileImage(@RequestParam("profileImage") MultipartFile file,
			@RequestHeader(value = "caller", required = false) String caller) throws IOException {

		logger.info(Constants.BEGIN + " UPLOAD -> /profile/uploadProfileImage - Caller [" + caller + "]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.GET_PROFILE);
			return new ResponseEntity<byte[]>(HttpStatus.OK);
		}
		
		// First find admin by username or pseudo
		Optional<ProfileImage> fileOptional = profileImageRepository.findById(admin.getIdAdmin());

		ProfileImage image = fileOptional.get();
		image.setMimetype(file.getContentType());
		image.setName(file.getName());
		image.setPicture(file.getBytes());
		
		ProfileImage profileImage = profileImageRepository.save(image);
		
		if (profileImage == null) {
			logger.error(ResponseCodes.ERROR_INSERT_PROFILE_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(admin.getIdAdmin()),
					admin.getNomAdmin(), caller, UserOperationsCodes.PROFILE_REPORT_INSERT);
			return new ResponseEntity<byte[]>(HttpStatus.OK);
		}

		logger.info(Constants.END + " UPLOAD -> /profile/uploadProfileImage - Caller [" + caller + "]");
		
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(admin.getIdAdmin()),
				admin.getNomAdmin(), caller, UserOperationsCodes.PROFILE_REPORT_INSERT);
		return new ResponseEntity<byte[]>(HttpStatus.OK);
	}
}