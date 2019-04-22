package com.vane.ophthacare.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Administrateur")
public class Administrateur implements Serializable {

	private static final long serialVersionUID = 6501686897066383042L;
	
	@Id
	@Column(name="id_admin")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdmin;
	
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
	private Date lastLoginAdmin;
	
	@Column(name="registration_admin")
	private Date registrationAdmin;
	
	/**
	 * @return the idAdmin
	 */
	public Long getIdAdmin() {
		return idAdmin;
	}
	/**
	 * @param idAdmin the idAdmin to set
	 */
	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}
	/**
	 * @return the nomAdmin
	 */
	public String getNomAdmin() {
		return nomAdmin;
	}
	/**
	 * @param nomAdmin the nomAdmin to set
	 */
	public void setNomAdmin(String nomAdmin) {
		this.nomAdmin = nomAdmin;
	}
	/**
	 * @return the prenomAdmin
	 */
	public String getPrenomAdmin() {
		return prenomAdmin;
	}
	/**
	 * @param prenomAdmin the prenomAdmin to set
	 */
	public void setPrenomAdmin(String prenomAdmin) {
		this.prenomAdmin = prenomAdmin;
	}
	/**
	 * @return the pseudoAdmin
	 */
	public String getPseudoAdmin() {
		return pseudoAdmin;
	}
	/**
	 * @param pseudoAdmin the pseudoAdmin to set
	 */
	public void setPseudoAdmin(String pseudoAdmin) {
		this.pseudoAdmin = pseudoAdmin;
	}
	/**
	 * @return the roleAdmin
	 */
	public String getRoleAdmin() {
		return roleAdmin;
	}
	/**
	 * @param roleAdmin the roleAdmin to set
	 */
	public void setRoleAdmin(String roleAdmin) {
		this.roleAdmin = roleAdmin;
	}
	/**
	 * @return the isActiveAdmin
	 */
	public String getActiveAdmin() {
		return activeAdmin;
	}
	/**
	 * @param isActiveAdmin the isActiveAdmin to set
	 */
	public void setActiveAdmin(String activeAdmin) {
		this.activeAdmin = activeAdmin;
	}
	/**
	 * @return the lastLoginAdmin
	 */
	public Date getLastLoginAdmin() {
		return lastLoginAdmin;
	}
	/**
	 * @param lastLoginAdmin the lastLoginAdmin to set
	 */
	public void setLastLoginAdmin(Date lastLoginAdmin) {
		this.lastLoginAdmin = lastLoginAdmin;
	}
	/**
	 * @return the registrationAdmin
	 */
	public Date getRegistrationAdmin() {
		return registrationAdmin;
	}
	/**
	 * @param registrationAdmin the registrationAdmin to set
	 */
	public void setRegistrationAdmin(Date registrationAdmin) {
		this.registrationAdmin = registrationAdmin;
	}
}
