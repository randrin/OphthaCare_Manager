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
@Table(name = "Professions")
public class ProfessionMedecin implements Serializable {

	private static final long serialVersionUID = -2535009268345858580L;

	@Id
	@Column(name = "id_profession")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProfession;

	@Column(name = "nom_profession")
	private String nomProfession;

	@Column(name = "desc_profession", length = 1000)
	private String descProfession;

	@Column(name = "code_profession")
	private String codeProfession;

	private Medecin medecin;
	
	/**
	 * @return the idProfession
	 */
	public Integer getIdProfession() {
		return idProfession;
	}

	/**
	 * @param idProfession the idProfession to set
	 */
	public void setIdProfession(Integer idProfession) {
		this.idProfession = idProfession;
	}

	/**
	 * @return the nomProfession
	 */
	@ExcelField(field = "Nom Profession", position = 1)
	public String getNomProfession() {
		return nomProfession;
	}

	/**
	 * @param nomProfession the nomProfession to set
	 */
	@ExcelField(field = "Nom Profession", position = 1)
	public void setNomProfession(String nomProfession) {
		this.nomProfession = nomProfession;
	}

	/**
	 * @return the descProfession
	 */
	@ExcelField(field = "Description Profession", position = 2)
	public String getDescProfession() {
		return descProfession;
	}

	/**
	 * @param descProfession the descProfession to set
	 */
	@ExcelField(field = "Description Profession", position = 2)
	public void setDescProfession(String descProfession) {
		this.descProfession = descProfession;
	}

	/**
	 * @return the codeProfession
	 */
	@ExcelField(field = "Code Profession", position = 0)
	public String getCodeProfession() {
		return codeProfession;
	}

	/**
	 * @param codeProfession the codeProfession to set
	 */
	@ExcelField(field = "Code Profession", position = 0)
	public void setCodeProfession(String codeProfession) {
		this.codeProfession = codeProfession;
	}
}
