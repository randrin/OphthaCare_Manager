package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vane.ophthacare.excel.export.ExcelField;

@Entity
@Table(name = "Maladies")
public class Maladie implements Serializable {

	private static final long serialVersionUID = -3258283463796790707L;

	@Id
	@Column(name = "id_maladie")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMaladie;

	@Column(name = "nom_maladie")
	private String nomMaladie;

	@Column(name = "description_maladie", length = 1000)
	private String descMaladie;

	@Column(name = "code_maladie")
	private String codeMaladie;

	@Column(name = "medecin_maladie")
	private String medecinMaladie;

	private Patient patient;
	
	/**
	 * @return the idMaladie
	 */
	public Integer getIdMaladie() {
		return idMaladie;
	}

	/**
	 * @param idMaladie the idMaladie to set
	 */
	public void setIdMaladie(Integer idMaladie) {
		this.idMaladie = idMaladie;
	}

	/**
	 * @return the nomMaladie
	 */
	@ExcelField(field = "Nom Maladie", position = 1)
	public String getNomMaladie() {
		return nomMaladie;
	}

	/**
	 * @param nomMaladie the nomMaladie to set
	 */
	@ExcelField(field = "Nom Maladie", position = 1)
	public void setNomMaladie(String nomMaladie) {
		this.nomMaladie = nomMaladie;
	}

	/**
	 * @return the descMaladie
	 */
	@ExcelField(field = "Description Maladie", position = 2)
	public String getDescMaladie() {
		return descMaladie;
	}

	/**
	 * @param descMaladie the descMaladie to set
	 */
	@ExcelField(field = "Description Maladie", position = 2)
	public void setDescMaladie(String descMaladie) {
		this.descMaladie = descMaladie;
	}

	/**
	 * @return the codeMaladie
	 */
	@ExcelField(field = "Code Maladie", position = 0)
	public String getCodeMaladie() {
		return codeMaladie;
	}

	/**
	 * @param codeMaladie the codeMaladie to set
	 */
	@ExcelField(field = "Code Maladie", position = 0)
	public void setCodeMaladie(String codeMaladie) {
		this.codeMaladie = codeMaladie;
	}

	/**
	 * @return the medecinMaladie
	 */
	@ExcelField(field = "Spécialiste Maladie", position = 3)
	public String getMedecinMaladie() {
		return medecinMaladie;
	}

	/**
	 * @param medecinMaladie the medecinMaladie to set
	 */
	@ExcelField(field = "Description Maladie", position = 2)
	public void setMedecinMaladie(String medecinMaladie) {
		this.medecinMaladie = medecinMaladie;
	}
}
