package com.vane.ophthacare.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vane.ophthacare.excel.export.ExcelField;

@Entity
@Table(name = "Medecins")
public class Medecin implements Serializable {

	private static final long serialVersionUID = -4281958355038427867L;

	@Id
	@Column(name = "id_medecin")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedecin;

	@Column(name = "nom_medecin")
	private String nomMedecin;

	@Column(name = "prenom_medecin")
	private String prenomMedecin;

	@Column(name = "date_medecin_patient")
	private String dateNaisMedecin;

	@Column(name = "age_medecin")
	private int ageMedecin;

	@Column(name = "sexe_medecin")
	private String sexeMedecin;

	@Column(name = "matricule_medecin")
	private String matriculeMedecin;

	@Column(name = "profession_medecin")
	private String professionMedecin;

	@Column(name = "email_medecin", length = 25)
	private String emailMedecin;

	@Column(name = "numTel_medecin")
	private BigInteger numTelMedecin;

	@Column(name = "numFixe_medecin")
	private BigInteger numFixeMedecin;

	@Column(name = "active")
	private String activeMedecin;

	// @OneToMany(mappedBy = "medecin", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	// Set<ProfessionMedecin> ProfessionMedecins;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_id")
	private ProfileImage profileMedecin;
	
	@Transient
	private String token;

	@Transient
	private Date tokenDate;

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
	@ExcelField(field = "Nom", position = 0)
	public String getNomMedecin() {
		return nomMedecin;
	}

	/**
	 * @param nomMedecin the nomMedecin to set
	 */
	@ExcelField(field = "Nom", position = 0)
	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	/**
	 * @return the prenomMedecin
	 */
	@ExcelField(field = "Prènom", position = 1)
	public String getPrenomMedecin() {
		return prenomMedecin;
	}

	/**
	 * @param prenomMedecin the prenomMedecin to set
	 */
	@ExcelField(field = "Prènom", position = 1)
	public void setPrenomMedecin(String prenomMedecin) {
		this.prenomMedecin = prenomMedecin;
	}

	/**
	 * @return the dateNaisMedecin
	 */
	@ExcelField(field = "Date de Naissance", position = 4)
	public String getDateNaisMedecin() {
		return dateNaisMedecin;
	}

	/**
	 * @param dateNaisMedecin the dateNaisMedecin to set
	 */
	@ExcelField(field = "Date de Naissance", position = 4)
	public void setDateNaisMedecin(String dateNaisMedecin) {
		this.dateNaisMedecin = dateNaisMedecin;
	}

	/**
	 * @return the ageMedecin
	 */
	@ExcelField(field = "Age", position = 5)
	public int getAgeMedecin() {
		return ageMedecin;
	}

	/**
	 * @param ageMedecin the ageMedecin to set
	 */
	@ExcelField(field = "Age", position = 5)
	public void setAgeMedecin(int ageMedecin) {
		this.ageMedecin = ageMedecin;
	}

	/**
	 * @return the sexeMedecin
	 */
	@ExcelField(field = "Sexe", position = 6)
	public String getSexeMedecin() {
		return sexeMedecin;
	}

	/**
	 * @param sexeMedecin the sexeMedecin to set
	 */
	@ExcelField(field = "Sexe", position = 6)
	public void setSexeMedecin(String sexeMedecin) {
		this.sexeMedecin = sexeMedecin;
	}

	/**
	 * @return the matriculeMedecin
	 */
	@ExcelField(field = "Matricule", position = 3)
	public String getMatriculeMedecin() {
		return matriculeMedecin;
	}

	/**
	 * @param matriculeMedecin the matriculeMedecin to set
	 */
	@ExcelField(field = "Matricule", position = 3)
	public void setMatriculeMedecin(String matriculeMedecin) {
		this.matriculeMedecin = matriculeMedecin;
	}

	/**
	 * @return the professionMedecin
	 */
	@ExcelField(field = "Profession", position = 2)
	public String getProfessionMedecin() {
		return professionMedecin;
	}

	/**
	 * @param professionMedecin the professionMedecin to set
	 */
	@ExcelField(field = "Profession", position = 2)
	public void setProfessionMedecin(String professionMedecin) {
		this.professionMedecin = professionMedecin;
	}

	/**
	 * @return the emailMedecin
	 */
	@ExcelField(field = "Email", position = 7)
	public String getEmailMedecin() {
		return emailMedecin;
	}

	/**
	 * @param emailMedecin the emailMedecin to set
	 */
	@ExcelField(field = "Email", position = 7)
	public void setEmailMedecin(String emailMedecin) {
		this.emailMedecin = emailMedecin;
	}

	/**
	 * @return the numTelMedecin
	 */
	@ExcelField(field = "Numèro Télephone", position = 8)
	public BigInteger getNumTelMedecin() {
		return numTelMedecin;
	}

	/**
	 * @param numTelMedecin the numTelMedecin to set
	 */
	@ExcelField(field = "Numèro Télephone", position = 8)
	public void setNumTelMedecin(BigInteger numTelMedecin) {
		this.numTelMedecin = numTelMedecin;
	}

	/**
	 * @return the numFixeMedecin
	 */
	@ExcelField(field = "Numèro Fixe", position = 9)
	public BigInteger getNumFixeMedecin() {
		return numFixeMedecin;
	}

	/**
	 * @param numFixeMedecin the numFixeMedecin to set
	 */
	@ExcelField(field = "Numèro Fixe", position = 9)
	public void setNumFixeMedecin(BigInteger numFixeMedecin) {
		this.numFixeMedecin = numFixeMedecin;
	}

	/**
	 * @return the activeMedecin
	 */
	public String getActiveMedecin() {
		return activeMedecin;
	}

	/**
	 * @param activeMedecin the activeMedecin to set
	 */
	public void setActiveMedecin(String activeMedecin) {
		this.activeMedecin = activeMedecin;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the tokenDate
	 */
	public Date getTokenDate() {
		return tokenDate;
	}

	/**
	 * @param tokenDate the tokenDate to set
	 */
	public void setTokenDate(Date tokenDate) {
		this.tokenDate = tokenDate;
	}
}
