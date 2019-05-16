package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Medecins")
public class Specialiste implements Serializable {

	private static final long serialVersionUID = -4281958355038427867L;

	@Id
	@Column(name="id_medecin")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedecin;
	
	@Column(name="nom_medecin")
	private String nomMedecin;
	
	@Column(name="prenom_medecin")
	private String prenomMedecin;
	
	@Column(name="pseudo_medecin")
	private String pseudoMedecin;
	
	@Column(name="matricule_medecin")
	private String matriculeMedecin;
	
	@Column(name="profession_medecin")
	private String professionMedecin;

	/**
	 * @return the idMedecin
	 */
	public Integer getIdMedecin() {
		return idMedecin;
	}

	/**
	 * @param idMedecin the idMedecin to set
	 */
	public void setIdMedecin(Integer idMedecin) {
		this.idMedecin = idMedecin;
	}

	/**
	 * @return the nomMedecin
	 */
	public String getNomMedecin() {
		return nomMedecin;
	}

	/**
	 * @param nomMedecin the nomMedecin to set
	 */
	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	/**
	 * @return the prenomMedecin
	 */
	public String getPrenomMedecin() {
		return prenomMedecin;
	}

	/**
	 * @param prenomMedecin the prenomMedecin to set
	 */
	public void setPrenomMedecin(String prenomMedecin) {
		this.prenomMedecin = prenomMedecin;
	}

	/**
	 * @return the pseudoMedecin
	 */
	public String getPseudoMedecin() {
		return pseudoMedecin;
	}

	/**
	 * @param pseudoMedecin the pseudoMedecin to set
	 */
	public void setPseudoMedecin(String pseudoMedecin) {
		this.pseudoMedecin = pseudoMedecin;
	}

	/**
	 * @return the matriculeMedecin
	 */
	public String getMatriculeMedecin() {
		return matriculeMedecin;
	}

	/**
	 * @param matriculeMedecin the matriculeMedecin to set
	 */
	public void setMatriculeMedecin(String matriculeMedecin) {
		this.matriculeMedecin = matriculeMedecin;
	}

	/**
	 * @return the professionMedecin
	 */
	public String getProfessionMedecin() {
		return professionMedecin;
	}

	/**
	 * @param professionMedecin the professionMedecin to set
	 */
	public void setProfessionMedecin(String professionMedecin) {
		this.professionMedecin = professionMedecin;
	}
}
