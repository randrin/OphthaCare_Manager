package com.vane.ophthacare.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.ProfileImage;
import com.vane.ophthacare.repository.AdministrateurRepository;
import com.vane.ophthacare.repository.ProfileImageRepository;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ProfileImageController {

	private static final Logger logger = LoggerFactory.getLogger(ProfileImageController.class);

	@Autowired
	private ProfileImageRepository profileImageRepository;

	@Autowired
	private AdministrateurRepository administrateurRepository;

	@GetMapping("/profile")
	public ResponseEntity<byte[]> getProfileImage(@RequestParam("username") String username) {

		//First find admin by username or pseudo
		Administrateur admin = administrateurRepository.findByPseudoAdmin(username);
		Optional<ProfileImage> fileOptional = profileImageRepository.findById(admin.getIdAdmin());

		if (fileOptional.isPresent()) {
			ProfileImage image = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
					.body(image.getPicture());
		}

		return ResponseEntity.status(404).body(null);
	}
}