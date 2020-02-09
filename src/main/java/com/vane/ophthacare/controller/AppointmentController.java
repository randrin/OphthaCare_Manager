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
import org.springframework.web.bind.annotation.RestController;

import com.vane.ophthacare.exception.Response;
import com.vane.ophthacare.exception.ResponseCodes;
import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.Appointment;
import com.vane.ophthacare.operations.UserOperations;
import com.vane.ophthacare.operations.UserOperationsCodes;
import com.vane.ophthacare.repository.AppointmentRepository;
import com.vane.ophthacare.utils.Constants;
import com.vane.ophthacare.utils.Utils;
	
@RestController
@CrossOrigin
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserOperations userOperations;
	
	public List<Appointment> appointmentList = new ArrayList<>();
	
	SimpleDateFormat format = new SimpleDateFormat(Constants.FORMAT_DATETIME);
	
	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@GetMapping("/getAllAppointments")
	public ResponseEntity<Object> getAllAppointments(@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " GET -> /appointment/getAllAppointments - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.APPOINTMENT_GET_ALL);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		List<Appointment> listAppointments = appointmentRepository.findAll();

		if (listAppointments == null) {
			userOperations.saveOperationReport(Constants.FAILED, caller, UserOperationsCodes.APPOINTMENT_GET_ALL);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GET_APPOINTMENTS_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " GET -> /appointment/getAllAppointments - Caller [" + caller + "]");
		return new ResponseEntity<Object>(listAppointments, HttpStatus.OK);
	}
	
	@PutMapping("/insert")
	public ResponseEntity<Object> insertAppointment(@RequestBody Appointment appointment,
			@RequestHeader(value = "caller", required = false) String caller) {
		
		logger.info(Constants.BEGIN + " UPDATE -> /appointment/insert - Caller [" + caller + "]");
		
		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
					appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if (appointment == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
					appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(appointment, false);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
					appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		// Check if Appointment not already taken in the same date
		// TODO
		//
		
		// Set Model Appointment
		appointment.setDateAppointment(format.format(GregorianCalendar.getInstance().getTime()));
		
		Appointment apt = appointmentRepository.save(appointment);
		
		if (apt == null) {
			logger.error(ResponseCodes.ERROR_SET_APPOINTMENT_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
				appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_INSERT);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_SET_APPOINTMENT_DB), HttpStatus.OK);
		}

		logger.info(Constants.END + " INSERT -> /appointment/insert - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(appointment.getIdAppointment()),
			appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_INSERT);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_INSERT_APPOINTMENT), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> updateAppointment(@RequestBody Appointment appointment,
			@RequestHeader(value = "caller", required = false) String caller) {
		
		logger.info(Constants.BEGIN + " UPDATE -> /appointment/update - Caller [" + caller + "]");

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
				appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}
		
		if (appointment == null) {
			logger.error(ResponseCodes.ERROR_PARSE_OBJECT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
				appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_PARSE_OBJECT), HttpStatus.OK);
		}
		
		String erreur = Utils.checkAttributeFromObject(appointment, false);
		
		if (erreur != "OK") {
			logger.error(ResponseCodes.ERROR_GENERIC.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
				appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_GENERIC), HttpStatus.OK);
		}
		
		Appointment apt = appointmentRepository.save(appointment);
		
		if (apt == null) {
			logger.error(ResponseCodes.ERROR_MODIFY_APPOINTMENT_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(appointment.getIdAppointment()),
				appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_UPDATE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_MODIFY_APPOINTMENT_DB), HttpStatus.OK);
		}
		
		logger.info(Constants.END + " UPDATE -> /appointment/update - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(appointment.getIdAppointment()),
				appointment.getTitle(), caller, UserOperationsCodes.APPOINTMENT_REPORT_UPDATE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_MODIFY_APPOINTMENT), HttpStatus.OK);
	}
	
	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<Object> deleteAppointment(@PathVariable("id") String id,
			@RequestHeader(value = "caller", required = false) String caller) {

		logger.info(Constants.BEGIN + " DELETE -> /appointment/delete/{id} - Caller [" + caller + "]");

		if (id == null) {
			logger.error(ResponseCodes.ERROR_INVALID_INPUT.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
				UserOperationsCodes.APPOINTMENT_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_INVALID_INPUT), HttpStatus.OK);
		}

		if (StringUtils.isEmpty(caller)) {
			logger.error(ResponseCodes.ERROR_CALLER_MISSING.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.APPOINTMENT_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_CALLER_MISSING), HttpStatus.OK);
		}

		try {
			appointmentRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(ResponseCodes.ERROR_DELETE_APPOINTMENT_DB.toString());
			userOperations.saveOperationReport(Constants.FAILED, String.valueOf(id), caller,
					UserOperationsCodes.APPOINTMENT_REPORT_DELETE);
			return new ResponseEntity<Object>(new Response(ResponseCodes.ERROR_DELETE_APPOINTMENT_DB), HttpStatus.OK);
		}

		Iterator<Appointment> iter = appointmentList.iterator();

		while (iter.hasNext()) {
			Appointment apt = iter.next();
			if (apt.getIdAppointment().equals(Integer.parseInt(id))) {
				iter.remove();
			}
		}

		logger.info(Constants.END + " DELETE -> /appointment/delete/{id} - Caller [" + caller + "]");
		userOperations.saveOperationReport(Constants.SUCCESS, String.valueOf(id), caller,
				UserOperationsCodes.APPOINTMENT_REPORT_DELETE);
		return new ResponseEntity<Object>(new Response(ResponseCodes.OK_DELETE_APPOINTMENT), HttpStatus.OK);
	}
}
