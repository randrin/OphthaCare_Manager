package com.vane.ophthacare.operations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.Report;
import com.vane.ophthacare.repository.ReportRepository;
import com.vane.ophthacare.utils.Constants;

@Repository
public class UserOperations {

	@Autowired
	public ReportRepository reportRepository;

	SimpleDateFormat format = new SimpleDateFormat(Constants.FORMAT_DATETIME);

	public static final Logger logger = LoggerFactory.getLogger(UserOperations.class);

	public String logOperationString(String status, String id, String cardName, String caller,
			UserOperationsCodes enumOperations) {
		return "[" + caller + "] " + String.format(enumOperations.getMessage(), id, cardName) + " [" + status + "]";
	}

	public String logOperationString(String status, String idOrcardName, String caller,
			UserOperationsCodes enumOperations) {
		return "[" + caller + "] " + String.format(enumOperations.getMessage(), idOrcardName) + " [" + status + "]";
	}

	public String logOperationString(String status, String caller, UserOperationsCodes enumOperations) {
		return "[" + caller + "] " + enumOperations.getMessage() + " [" + status + "]";
	}

	public void saveOperationReport(String status, String id, String cardName, String caller,
			UserOperationsCodes enumOperations) {

		Report entity = new Report();
		entity.setActionReport(String.format(enumOperations.getMessage(), id, cardName));
		entity.setUserReport(caller);
		entity.setResultReport(status);
		entity.setDateReport(format.format(new Date()));

		logger.info(entity.toString());
		reportRepository.save(entity);
	}

	public void saveOperationReport(String status, String cardName, String caller, UserOperationsCodes enumOperations) {

		Report entity = new Report();
		entity.setActionReport(String.format(enumOperations.getMessage(), cardName));
		entity.setUserReport(caller);
		entity.setResultReport(status);
		entity.setDateReport(format.format(new Date()));

		logger.info(entity.toString());
		reportRepository.save(entity);
	}

	public void saveOperationReport(String status, String caller, UserOperationsCodes enumOperations) {

		Report entity = new Report();
		entity.setActionReport(String.format(enumOperations.getMessage()));
		entity.setUserReport(caller);
		entity.setResultReport(status);
		entity.setDateReport(format.format(new Date()));

		logger.info(entity.toString());
		reportRepository.save(entity);
	}
}
