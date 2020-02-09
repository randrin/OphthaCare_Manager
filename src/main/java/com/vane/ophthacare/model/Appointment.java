package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Appointments")
public class Appointment implements Serializable {

	private static final long serialVersionUID = 3915651952309317547L;

	@Id
	@Column(name = "id_appointment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAppointment;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;

	@Column(name = "date_appointment")
	private String dateAppointment;
	
	@Column(name = "start_hour")
	private String startHourAppointment;
	
	@Column(name = "end_hour")
	private String endHourAppointment;
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the idAppointment
	 */
	public Integer getIdAppointment() {
		return idAppointment;
	}

	/**
	 * @param idAppointment the idAppointment to set
	 */
	public void setIdAppointment(Integer idAppointment) {
		this.idAppointment = idAppointment;
	}

	/**
	 * @return the dateAppointment
	 */
	public String getDateAppointment() {
		return dateAppointment;
	}

	/**
	 * @param dateAppointment the dateAppointment to set
	 */
	public void setDateAppointment(String dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	/**
	 * @return the startHourAppointment
	 */
	public String getStartHourAppointment() {
		return startHourAppointment;
	}

	/**
	 * @param startHourAppointment the startHourAppointment to set
	 */
	public void setStartHourAppointment(String startHourAppointment) {
		this.startHourAppointment = startHourAppointment;
	}

	/**
	 * @return the endHourAppointment
	 */
	public String getEndHourAppointment() {
		return endHourAppointment;
	}

	/**
	 * @param endHourAppointment the endHourAppointment to set
	 */
	public void setEndHourAppointment(String endHourAppointment) {
		this.endHourAppointment = endHourAppointment;
	}
	
}
