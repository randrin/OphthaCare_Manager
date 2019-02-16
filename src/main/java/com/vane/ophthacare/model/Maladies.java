package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Maladies")
public class Maladies implements Serializable {

	private static final long serialVersionUID = -3258283463796790707L;
	
	@Id
	@Column(name = "id_maladie")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idMaladie;

	@Column(name="patient_maladies")
	private long idPatient;
	
	@Column(name = "nom_maladie")
	private String nomMaladie;
	
	@Column(name = "description_maladie")
	private String descMaladie;

	/**
	 * @return the idMaladie
	 */
	public long getIdMaladie() {
		return idMaladie;
	}

	/**
	 * @param idMaladie the idMaladie to set
	 */
	public void setIdMaladie(long idMaladie) {
		this.idMaladie = idMaladie;
	}

	/**
	 * @return the nomMaladie
	 */
	public String getNomMaladie() {
		return nomMaladie;
	}

	/**
	 * @param nomMaladie the nomMaladie to set
	 */
	public void setNomMaladie(String nomMaladie) {
		this.nomMaladie = nomMaladie;
	}

	/**
	 * @return the descMaladie
	 */
	public String getDescMaladie() {
		return descMaladie;
	}

	/**
	 * @param descMaladie the descMaladie to set
	 */
	public void setDescMaladie(String descMaladie) {
		this.descMaladie = descMaladie;
	}
	
}
