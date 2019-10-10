package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProfileImage")
public class ProfileImage implements Serializable {

	private static final long serialVersionUID = 5140658863493524983L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "mimetype")
	private String mimetype;

	@Lob
	@Column(name = "picture")
	private byte[] picture;

	@OneToOne(mappedBy = "profileAdmin")
	private Administrateur administrateur;

	//@OneToOne(mappedBy = "profilePatient")
	//private Patient patient;
	
	//@OneToOne(mappedBy = "profileMedecin")
	//private Medecin medecin;
	
	public ProfileImage() {
	}

	/**
	 * @param name
	 * @param mimetype
	 * @param picture
	 */
	public ProfileImage(String name, String mimetype, byte[] picture) {
		this.name = name;
		this.mimetype = mimetype;
		this.picture = picture;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mimetype
	 */
	public String getMimetype() {
		return mimetype;
	}

	/**
	 * @param mimetype the mimetype to set
	 */
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	/**
	 * @return the picture
	 */
	public byte[] getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	/**
	 * @return the administrateur
	 */
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	/**
	 * @param administrateur the administrateur to set
	 */
	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}
}