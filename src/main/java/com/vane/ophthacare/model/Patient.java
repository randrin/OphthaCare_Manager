package com.vane.ophthacare.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.vane.ophthacare.excel.export.ExcelField;

@Entity
@Table(name="Patients")
public class Patient implements Serializable {

	private static final long serialVersionUID = -7687560243779956203L;

	@Id
	@Column(name="id_patient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPatient;
	
	@Column(name="code_patient")
	private String codePatient;

	@Column(name="nom_patient")
	private String nomPatient;
	
	@Column(name="prenom_patient")
	private String prenomPatient;
	
	@Column(name="date_nais_patient")
	private String dateNaisPatient;
	
	@Column(name="age_patient")
	private int agePatient;
	
	@Column(name="sexe_patient")
	private String sexePatient;
	
	@Column(name="addresse_patient")
	private String addressePatient;
	
	@Column(name="codPost_patient")
	private Integer codePostPatient;
	
	@Column(name="domicile_patient")
	private String domicilePatient;
	
	@Column(name="infoSuppl_patient")
	private String infoSupplPatient;
	
	@Column(name="email_patient", length = 25)
	private String emailPatient;
	
	@Column(name="numTel_patient")
	private BigInteger numTelPatient;
	
	@Column(name="numFixe_patient")
	private BigInteger numFixePatient;

	//@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	//@JoinColumn(name="patient_maladies")
	//List<Maladies> maladie;
	
	/**
	 * @return the idPatient
	 */
	public Integer getIdPatient() {
		return idPatient;
	}

	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}

	/**
	 * @return the codePatient
	 */
	public String getCodePatient() {
		return codePatient;
	}

	/**
	 * @param codePatient the codePatient to set
	 */
	public void setCodePatient(String codePatient) {
		this.codePatient = codePatient;
	}
	
	/**
	 * @return the nomPatient
	 */
	@ExcelField(field = "Nom", position = 0)
	public String getNomPatient() {
		return nomPatient;
	}

	/**
	 * @param nomPatient the nomPatient to set
	 */
	@ExcelField(field = "Nom", position = 0)
	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	/**
	 * @return the prenomPatient
	 */
	@ExcelField(field = "Prènom", position = 1)
	public String getPrenomPatient() {
		return prenomPatient;
	}

	/**
	 * @param prenomPatient the prenomPatient to set
	 */
	@ExcelField(field = "Prènom", position = 1)
	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}

	/**
	 * @return the dateNaisPatient
	 */
	@ExcelField(field = "Date de Naissance", position = 3)
	public String getDateNaisPatient() {
		return dateNaisPatient;
	}

	/**
	 * @param dateNaisPatient the dateNaisPatient to set
	 */
	@ExcelField(field = "Date de Naissance", position = 3)
	public void setDateNaisPatient(String dateNaisPatient) {
		this.dateNaisPatient = dateNaisPatient;
	}

	/**
	 * @return the agePatient
	 */
	@ExcelField(field = "Age", position = 4)
	public int getAgePatient() {
		return agePatient;
	}

	/**
	 * @param agePatient the agePatient to set
	 */
	@ExcelField(field = "Age", position = 4)
	public void setAgePatient(int agePatient) {
		this.agePatient = agePatient;
	}

	/**
	 * @return the sexePatient
	 */
	@ExcelField(field = "Sexe", position = 2)
	public String getSexePatient() {
		return sexePatient;
	}

	/**
	 * @param sexePatient the sexePatient to set
	 */
	@ExcelField(field = "Sexe", position = 2)
	public void setSexePatient(String sexePatient) {
		this.sexePatient = sexePatient;
	}

	/**
	 * @return the addressePatient
	 */
	@ExcelField(field = "Addresse", position = 9)
	public String getAddressePatient() {
		return addressePatient;
	}

	/**
	 * @param addressePatient the addressePatient to set
	 */
	@ExcelField(field = "Addresse", position = 9)
	public void setAddressePatient(String addressePatient) {
		this.addressePatient = addressePatient;
	}

	/**
	 * @return the codePostPatient
	 */
	@ExcelField(field = "Code Postale", position = 11)
	public Integer getCodePostPatient() {
		return codePostPatient;
	}

	/**
	 * @param codePostPatient the codePostPatient to set
	 */
	@ExcelField(field = "Code Postale", position = 11)
	public void setCodePostPatient(Integer codePostPatient) {
		this.codePostPatient = codePostPatient;
	}

	/**
	 * @return the domicilePatient
	 */
	@ExcelField(field = "Domicile", position = 10)
	public String getDomicilePatient() {
		return domicilePatient;
	}

	/**
	 * @param domicilePatient the domicilePatient to set
	 */
	@ExcelField(field = "Domicile", position = 10)
	public void setDomicilePatient(String domicilePatient) {
		this.domicilePatient = domicilePatient;
	}

	/**
	 * @return the infoSupplPatient
	 */
	@ExcelField(field = "Informations Supplémentaires", position = 12)
	public String getInfoSupplPatient() {
		return infoSupplPatient;
	}

	/**
	 * @param infoSupplPatient the infoSupplPatient to set
	 */
	@ExcelField(field = "Informations Supplémentaires", position = 12)
	public void setInfoSupplPatient(String infoSupplPatient) {
		this.infoSupplPatient = infoSupplPatient;
	}

	/**
	 * @return the emailPatient
	 */
	@ExcelField(field = "Adresse Electronique", position = 5)
	public String getEmailPatient() {
		return emailPatient;
	}

	/**
	 * @param emailPatient the emailPatient to set
	 */
	@ExcelField(field = "Adresse Electronique", position = 5)
	public void setEmailPatient(String emailPatient) {
		this.emailPatient = emailPatient;
	}

	/**
	 * @return the numTelPatient
	 */
	@ExcelField(field = "Numéro Cellulaire", position = 6)
	public BigInteger getNumTelPatient() {
		return numTelPatient;
	}

	/**
	 * @param numTelPatient the numTelPatient to set
	 */
	@ExcelField(field = "Numéro Cellulaire", position = 6)
	public void setNumTelPatient(BigInteger numTelPatient) {
		this.numTelPatient = numTelPatient;
	}

	/**
	 * @return the numFixePatient
	 */
	@ExcelField(field = "Numéro Fixe", position = 7)
	public BigInteger getNumFixePatient() {
		return numFixePatient;
	}

	/**
	 * @param numFixePatient the numFixePatient to set
	 */
	@ExcelField(field = "Numéro Fixe", position = 7)
	public void setNumFixePatient(BigInteger numFixePatient) {
		this.numFixePatient = numFixePatient;
	}

//	/**
//	 * @return the maladie
//	 */
//	public List<Maladies> getMaladie() {
//		return maladie;
//	}
//
//	/**
//	 * @param maladie the maladie to set
//	 */
//	public void setMaladie(List<Maladies> maladie) {
//		this.maladie = maladie;
//	}
}
