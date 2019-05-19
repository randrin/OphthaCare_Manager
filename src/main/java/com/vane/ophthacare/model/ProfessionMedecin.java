package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Professions")
public class ProfessionMedecin implements Serializable {

	private static final long serialVersionUID = -2535009268345858580L;

	@Id
	@Column(name="id_profession")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProfession;
	
	@Column(name="nom_profession")
	private String nomProfession;
	
	@Column(name="desc_profession")
	private String descProfession;
	
	@Column(name="code_profession")
	private String codeProfession;

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
	public String getNomProfession() {
		return nomProfession;
	}

	/**
	 * @param nomProfession the nomProfession to set
	 */
	public void setNomProfession(String nomProfession) {
		this.nomProfession = nomProfession;
	}

	/**
	 * @return the descProfession
	 */
	public String getDescProfession() {
		return descProfession;
	}

	/**
	 * @param descProfession the descProfession to set
	 */
	public void setDescProfession(String descProfession) {
		this.descProfession = descProfession;
	}

	/**
	 * @return the codeProfession
	 */
	public String getCodeProfession() {
		return codeProfession;
	}

	/**
	 * @param codeProfession the codeProfession to set
	 */
	public void setCodeProfession(String codeProfession) {
		this.codeProfession = codeProfession;
	}
}
