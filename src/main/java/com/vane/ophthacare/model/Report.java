package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vane.ophthacare.excel.export.ExcelField;

@Entity
@Table(name = "Reports")
public class Report implements Serializable {

	private static final long serialVersionUID = 6788231499698266679L;

	@Id
	@Column(name = "id_report")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReport;

	@Column(name = "user_report")
	private String userReport;

	@Column(name = "action_report")
	private String actionReport;

	@Column(name = "result_report")
	private String resultReport;

	@Column(name = "date_report")
	private String dateReport;

	/**
	 * @return the idReport
	 */
	public Integer getIdReport() {
		return idReport;
	}

	/**
	 * @param idReport the idReport to set
	 */
	public void setIdReport(Integer idReport) {
		this.idReport = idReport;
	}

	/**
	 * @return the userReport
	 */
	@ExcelField(field = "Nom Utilisateur", position = 0)
	public String getUserReport() {
		return userReport;
	}

	/**
	 * @param userReport the userReport to set
	 */
	@ExcelField(field = "Nom Utilisateur", position = 0)
	public void setUserReport(String userReport) {
		this.userReport = userReport;
	}

	/**
	 * @return the actionReport
	 */
	@ExcelField(field = "Opération Utilisateur", position = 1)
	public String getActionReport() {
		return actionReport;
	}

	/**
	 * @param actionReport the actionReport to set
	 */
	@ExcelField(field = "Opération Utilisateur", position = 1)
	public void setActionReport(String actionReport) {
		this.actionReport = actionReport;
	}

	/**
	 * @return the resultReport
	 */
	@ExcelField(field = "Resultat Opération", position = 2)
	public String getResultReport() {
		return resultReport;
	}

	/**
	 * @param resultReport the resultReport to set
	 */
	@ExcelField(field = "Resultat Opération", position = 2)
	public void setResultReport(String resultReport) {
		this.resultReport = resultReport;
	}

	/**
	 * @return the dateReport
	 */
	@ExcelField(field = "Date Opération", position = 3)
	public String getDateReport() {
		return dateReport;
	}

	/**
	 * @param dateReport the dateReport to set
	 */
	@ExcelField(field = "Date Opération", position = 3)
	public void setDateReport(String dateReport) {
		this.dateReport = dateReport;
	}
}
