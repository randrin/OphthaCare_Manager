package com.vane.ophthacare.operations;

public enum UserOperationsCodes {

	// Patients
	PATIENT_GET_ALL("Get all Patients from DB"),
	PATIENT_REPORT_INSERT("Insert new Patient on DB with id [%s] and pseudo [%s]"),
	PATIENT_REPORT_UPDATE("Update Patient on DB with id [%s] and pseudo [%s]"),
	PATIENT_REPORT_DELETE("Deleted Patient from DB with id [%s]"),
	PATIENT_REPORT_FOUND("Patient already present. Try another one"),

	// Administrateurs
	ADMIN_GET_ALL("Get all Administrateurs from DB"), 
	ADMIN_DOWNLOAD_PDF("Download PDF Administrateurs"),
	ADMIN_REPORT_INSERT("Insert new Administrateur on DB with id [%s] and pseudo [%s]"),
	ADMIN_REPORT_UPDATE("Update Administrateur on DB with id [%s] and pseudo [%s]"),
	ADMIN_REPORT_DELETE("Deleted Administrateur from DB with id [%s]"),
	ADMIN_REPORT_FOUND("Administrateur already present. Try another one"),

	// Appointments
	APPOINTMENT_GET_ALL("Get all Appointments from DB"), 
	APPOINTMENT_REPORT_INSERT("Insert new Appointment on DB with id [%s] and title [%s]"),
	APPOINTMENT_REPORT_UPDATE("Update Appointment on DB with id [%s] and title [%s]"),
	APPOINTMENT_REPORT_DELETE("Deleted Appointment from DB with id [%s]"),
	
	// Report
	REPORT_GET_ALL("Get all Report from DB"),

	// Permissions
	PERMISSION_GET_ALL("Get all Permissions from DB"),
	PERMISSION_REPORT_INSERT("Insert new Permission on DB with id [%s] and name [%s]"),
	PERMISSION_REPORT_UPDATE("Update Permission on DB with id [%s] and name [%s]"),
	PERMISSION_REPORT_DELETE("Deleted Permission from DB with id [%s]"),
	PERMISSION_REPORT_FOUND("Permission already present. Try another one"),
		
	// Profile Image
	GET_PROFILE("Get Profile"),
	PROFILE_REPORT_INSERT("Insert profile on DB with id [%s] and pseudo [%s]"),
		
	// Specialistes-Medecins
	MEDECIN_GET_ALL("Get all Medecins from DB"),
	MEDECIN_REPORT_INSERT("Insert new Medecin on DB with id [%s] and pseudo [%s]"),
	MEDECIN_REPORT_UPDATE("Update Medecin on DB with id [%s] and pseudo [%s]"),
	MEDECIN_REPORT_DELETE("Deleted Medecin from DB with id [%s]"),

	// Maladies
	DISEASE_GET_ALL("Get all Diseases from DB"),
	DISEASE_REPORT_INSERT("Insert new Disease on DB with id [%s] and pseudo [%s]"),
	DISEASE_REPORT_UPDATE("Update Disease on DB with id [%s] and pseudo [%s]"),
	DISEASE_REPORT_DELETE("Deleted Disease from DB with id [%s]"),

	// Profession Medecins
	PROFESSION_GET_ALL("Get all Professions from DB"),
	PROFESSION_REPORT_INSERT("Insert new Profession on DB with id [%s] and pseudo [%s]"),
	PROFESSION_REPORT_UPDATE("Update Profession on DB with id [%s] and pseudo [%s]"),
	PROFESSION_REPORT_DELETE("Deleted Profession from DB with id [%s]"),

	// EXCELS IMPORT
	EXCEL_IMPORT_PATIENTS("Import File Excel Patients, with file [%s]"),
	EXCEL_IMPORT_ADMINISTRATEURS("Import File Excel Adminsitrateurs, with file [%s]"),
	EXCEL_IMPORT_DISEASES("Import File Excel Maldies, with file [%s]"),
	EXCEL_IMPORT_PROFESSIONS("Import File Excel Professions Maladies, with file [%s]"),
	EXCEL_IMPORT_MEDECINS("Import File Excel Médecins, with file [%s]"),
	EXCEL_IMPORT_REPORTS("Import File Excel Reports, with file [%s]"), 
	ERROR_EXCEL_IMPORT("Error Import File Excel"),

	// EXCELS EXPORT
	EXCEL_EXPORT_PATIENTS("Export File Excel Patients, by user [%s]"),
	EXCEL_EXPORT_ADMINISTRATEURS("Export File Excel Adminsitrateurs, by user [%s]"),
	EXCEL_EXPORT_DISEASES("Export File Excel Maladies, by user [%s]"),
	EXCEL_EXPORT_PROFESSIONS("Export File Excel Professions Médecins, by user [%s]"),
	EXCEL_EXPORT_MEDECINS("Export File Excel Médecins, by user [%s]"),
	EXCEL_EXPORT_REPORTS("Export File Excel Reports, by user [%s]"), 
	ERROR_EXCEL_EXPORT("Error Import File Excel"),

	MONITORING_CARD_GET_ALL("Get all Monitoring Card from DB"),
	MONITORING_CARD_DETAIL_GET_BY_ID("Get Monitoring Card Detail from DB with id [%s] and model [%s]"),
	MONITORING_CARD_GET_BY_ID("Get Monitoring Card from DB with id [%s] and model [%s]");

	private String message;

	private UserOperationsCodes(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
