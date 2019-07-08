package com.vane.ophthacare.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vane.ophthacare.excel.export.ExcelField;

@Entity
@Table(name="Administrateur")
public class Administrateur implements Serializable {

	private static final long serialVersionUID = 6501686897066383042L;
	
	@Id
	@Column(name="id_admin")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAdmin;
	
	@Column(name="nom_admin")
	private String nomAdmin;
	
	@Column(name="prenom_admin")
	private String prenomAdmin;
	
	@Column(name="pseudo_admin")
	private String pseudoAdmin;
	
	@Column(name="role")
	private String roleAdmin;
	
	@Column(name="active")
	private String activeAdmin;
	
	@Column(name="last_login")
	private String lastLoginAdmin;
	
	@Column(name="registration_admin")
	private String registrationAdmin;
	
	@Transient
	private String token;
	
	@Transient
	private Date tokenDate;
	
	/**
	 * @return the idAdmin
	 */
	public Integer getIdAdmin() {
		return idAdmin;
	}
	/**
	 * @param idAdmin the idAdmin to set
	 */
	public void setIdAdmin(Integer idAdmin) {
		this.idAdmin = idAdmin;
	}
	/**
	 * @return the nomAdmin
	 */
	@ExcelField(field = "Nom", position = 1)
	public String getNomAdmin() {
		return nomAdmin;
	}
	/**
	 * @param nomAdmin the nomAdmin to set
	 */
	@ExcelField(field = "Nom", position = 1)
	public void setNomAdmin(String nomAdmin) {
		this.nomAdmin = nomAdmin;
	}
	/**
	 * @return the prenomAdmin
	 */
	@ExcelField(field = "Prénom", position = 2)
	public String getPrenomAdmin() {
		return prenomAdmin;
	}
	/**
	 * @param prenomAdmin the prenomAdmin to set
	 */
	@ExcelField(field = "Prénom", position = 2)
	public void setPrenomAdmin(String prenomAdmin) {
		this.prenomAdmin = prenomAdmin;
	}
	/**
	 * @return the pseudoAdmin
	 */
	@ExcelField(field = "Pseudo", position = 0)
	public String getPseudoAdmin() {
		return pseudoAdmin;
	}
	/**
	 * @param pseudoAdmin the pseudoAdmin to set
	 */
	@ExcelField(field = "Pseudo", position = 0)
	public void setPseudoAdmin(String pseudoAdmin) {
		this.pseudoAdmin = pseudoAdmin;
	}
	/**
	 * @return the roleAdmin
	 */
	@ExcelField(field = "Rôle", position = 3)
	public String getRoleAdmin() {
		return roleAdmin;
	}
	/**
	 * @param roleAdmin the roleAdmin to set
	 */
	@ExcelField(field = "Rôle", position = 3)
	public void setRoleAdmin(String roleAdmin) {
		this.roleAdmin = roleAdmin;
	}
	/**
	 * @return the isActiveAdmin
	 */
	@ExcelField(field = "Activation", position = 4)
	public String getActiveAdmin() {
		return activeAdmin;
	}
	/**
	 * @param isActiveAdmin the isActiveAdmin to set
	 */
	@ExcelField(field = "Activation", position = 4)
	public void setActiveAdmin(String activeAdmin) {
		this.activeAdmin = activeAdmin;
	}
	/**
	 * @return the lastLoginAdmin
	 */
	@ExcelField(field = "Dernière Connexion", position = 6)
	public String getLastLoginAdmin() {
		return lastLoginAdmin;
	}
	/**
	 * @param lastLoginAdmin the lastLoginAdmin to set
	 */
	@ExcelField(field = "Dernière Connexion", position = 6)
	public void setLastLoginAdmin(String lastLoginAdmin) {
		this.lastLoginAdmin = lastLoginAdmin;
	}
	/**
	 * @return the registrationAdmin
	 */
	@ExcelField(field = "Date de Registration", position = 5)
	public String getRegistrationAdmin() {
		return registrationAdmin;
	}
	/**
	 * @param registrationAdmin the registrationAdmin to set
	 */
	@ExcelField(field = "Date de Registration", position = 5)
	public void setRegistrationAdmin(String registrationAdmin) {
		this.registrationAdmin = registrationAdmin;
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
