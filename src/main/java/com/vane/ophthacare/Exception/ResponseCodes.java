package com.vane.ophthacare.Exception;

public enum ResponseCodes {
	
	/******************************
	 *							  *
	 *       OK RESPONSE		  *
	 * 							  *
	 *****************************/
	// Patients
	OK_INSERT_PATIENT("OK","Patient added to DB"),
	OK_DELETE_PATIENT("OK","Patient deleted from DB"),
	OK_MODIFY_PATIENT("OK","Patient modified"),
	
	// Administrateurs
	OK_INSERT_ADMIN("OK","Admin added to DB"),
	OK_DELETE_ADMIN("OK","Admin deleted from DB"),
	OK_MODIFY_ADMIN("OK","Admin modified"),
	
	// Spécialités-Medecins
	OK_INSERT_SPECIALIST("OK","Specialist added to DB"),
	OK_MODIFY_SPECIALIST("OK","Specialist modified"),
	OK_DELETE_SPECIALIST("OK","Specialist deleted from DB"),
	
	// Maladies
	OK_INSERT_DISEASE("OK","Disease added to DB"),
	OK_DELETE_DISEASE("OK","Disease deleted from DB"),
	OK_MODIFY_DISEASE("OK","Disease modified"),
	
	// Others
	OK_EXCEL_FILE_NOT_EXIST("OK","Monitoring card does not exist. Adding new"),
	OK_INSERT_MONITORING_CARD("OK","Monitoring card inserted on DB"),
	OK_CHECK_MONITORING_CARD("OK","Check passed"),
	OK_INSERT_SKYDE("OK","SkyDE inserted on DB"),
	
	
	/******************************
	 *							  *
	 *       ERROR RESPONSE		  *
	 * 							  *
	 *****************************/
	// Patients
	ERROR_PATIENT_NO_PERMISSION("ERR0002","User without permissions. Contact your administrator"),
	ERROR_PATIENT_NOT_FOUND("ERR0003","User or Password not found on Clinic"),
	ERROR_GET_PATIENTS_DB("ERR0004","Error getting patients on DB"),
	ERROR_SET_PATIENT_DB("ERR0005","Error on insert patient on DB"),
	ERROR_DELETE_PATIENT_DB("ERR0006","Error on delete patient on DB"),
	ERROR_MODIFY_PATIENT_DB("ERR0007","Error on modify patient on DB"),
	
	// Administrateurs
	ERROR_GET_ADMINISTRATORS_DB("ERR0004","Error getting administrators on DB"),
	ERROR_SET_ADMIN_DB("ERR0005","Error on insert admin on DB"),
	ERROR_DELETE_ADMIN_DB("ERR0006","Error on delete admin on DB"),
	ERROR_MODIFY_ADMIN_DB("ERR0007","Error on modify admin on DB"),
	
	// Spécialités-Medecins
	ERROR_GET_SPECIALISTS_DB("ERR0004","Error getting specialists on DB"),
	ERROR_SET_SPECIALISTS_DB("ERR0005","Error on insert specialist on DB"),
	ERROR_DELETE_SPECIALIST_DB("ERR0006","Error on delete specialist on DB"),
	ERROR_MODIFY_SPECIALIST_DB("ERR0007","Error on modify specialist on DB"),
	
	// Maladies
	ERROR_GET_DISEASES_DB("ERR0004","Error getting diseases on DB"),
	ERROR_SET_DISEASE_DB("ERR0005","Error on insert disease on DB"),
	ERROR_DELETE_DISEASE_DB("ERR0006","Error on delete disease on DB"),
	ERROR_MODIFY_DISEASE_DB("ERR0007","Error on modify disease on DB"),
	
	// Others
	ERROR_GENERIC("ERR0001","Generic Error"),
	
	
	ERROR_PARSE_OBJECT("ERR0008","Error on parsing JSON Data"),
	ERROR_INVALID_INPUT("ERR0009","Invalid Parameters"),
	ERROR_SET_EMAIL_DB("ERR0011","Error on insert email on DB"),
	ERROR_GET_ASSETS_DB("ERR0012","Error getting assets on DB"),
	ERROR_SET_SOP_DB("ERR0013","Error on insert Alarm on DB"),
	ERROR_DELETE_SOP_DB("ERR0014","Error on delete sop on DB"),
	ERROR_GET_REPORT_DB("ERR0015","Error getting report on DB"),
	ERROR_EXCEL_CONCURRENCY_EXPORT("ERR0016","To many process for export excel... Wait many second"),
	ERROR_EXCEL_FILE_IMPORT_MISSING("ERR0017","Seleziona un file da aggiungere"),
	ERROR_EXCEL_ID_NULL("ERR0018","Campo 'Id Scheda Monitoraggio' vuoto."),
	ERROR_EXCEL_SHEET_NULL("ERR0019","Sheet 'Informazioni Scheda' non presente"),
	ERROR_EXCEL_FIELD_TITLE_NULL("ERR0020","Campo 'Titolo Scheda' vuoto"),
	ERROR_EXCEL_FIELD_VERSION_NULL("ERR0021","Campo 'Versione' vuoto"),
	ERROR_EXCEL_ID_NOT_NULL("ERR0022","Campo 'Id Scheda Monitoraggio' non vuoto"),
	ERROR_EXCEL_FILENAME_NO_MATCH("ERR0023","Campo 'Nome Scheda' e il nome del file excel non corrispondono"),
	ERROR_PARSING_EMAIL("ERR0024","Error parsing emails from DB"),
	ERROR_CALLER_MISSING("ERR0025","Caller is missing"),
	ERROR_EXCEL_CARD_NOT_FOUND("ERR0026","Card not found"),
	
	
	WARN_EXCEL_MONITORING_CARD_EXIST("WARN0001","Esiste già una scheda di monitoraggio con questo nome sul DB. Vuoi continuare l'inserimento?");
	
	
	private String code;
	private String message;
	
	ResponseCodes(String code, String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public ResponseCodes setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResponseCodes setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public String toString() {
		return this.getCode() + "-> Message: " + this.getMessage();
	}
}
