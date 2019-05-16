package com.vane.ophthacare.Exception;

public enum ResponseCodes {
	
	/******************************
	 * OK Response
	 *****************************/
	OK_INSERT_PATIENT("OK","Patient added to DB"),
	OK_DELETE_PATIENT("OK","Patient deleted from DB"),
	OK_MODIFY_PATIENT("OK","Patient modified"),
	OK_INSERT_ADMIN("OK","Admin added to DB"),
	OK_DELETE_ADMIN("OK","Admin deleted from DB"),
	OK_MODIFY_ADMIN("OK","Admin modified"),
	OK_INSERT_MEDECIN("OK","Medecin added to DB"),
	OK_MODIFY_MEDECIN("OK","Medecin modified"),
	OK_DELETE_MEDECIN("OK","Medecin deleted from DB"),
	OK_INSERT_SOP("OK","Alarm added to DB"),
	OK_DELETE_SOP("OK","Alarm deleted from DB"),
	OK_EXCEL_FILE_NOT_EXIST("OK","Monitoring card does not exist. Adding new"),
	OK_INSERT_MONITORING_CARD("OK","Monitoring card inserted on DB"),
	OK_CHECK_MONITORING_CARD("OK","Check passed"),
	OK_INSERT_SKYDE("OK","SkyDE inserted on DB"),
	
	/******************************
	 * ERROR Response
	 *****************************/
	ERROR_GENERIC("ERR0001","Generic Error"),
	ERROR_PATIENT_NO_PERMISSION("ERR0002","User without permissions. Contact your administrator"),
	ERROR_PATIENT_NOT_FOUND("ERR0003","User or Password not found on LDAP"),
	ERROR_GET_PATIENTS_DB("ERR0004","Error getting patients on DB"),
	ERROR_GET_SPECIALISTES_DB("ERR0004","Error getting specialits on DB"),
	ERROR_GET_DISEASES_DB("ERR0004","Error getting diseases on DB"),
	ERROR_SET_PATIENT_DB("ERR0005","Error on insert user on DB"),
	ERROR_DELETE_PATIENT_DB("ERR0006","Error on delete user on DB"),
	ERROR_DELETE_ADMIN_DB("ERR0006","Error on delete admin on DB"),
	ERROR_MODIFY_PATIENT_DB("ERR0007","Error on modify user on DB"),
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
