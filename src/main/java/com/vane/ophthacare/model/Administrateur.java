package com.vane.ophthacare.model;

import java.io.Serializable;

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
	@Column(name="id_patient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

}
