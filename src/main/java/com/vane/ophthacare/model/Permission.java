package com.vane.ophthacare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Permissions")
public class Permission implements Serializable {

	private static final long serialVersionUID = 6401179700598876641L;

	@Id
	@Column(name = "id_permission")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPermission;
	
	@Column(name = "nom_permission")
	private String nomPermission;
	
	@Column(name = "create_permission")
	private boolean createPermission;
	
	@Column(name = "read_permission")
	private boolean readPermission;
	
	@Column(name = "update_permission")
	private boolean updatePermission;
	
	@Column(name = "delete_permission")
	private boolean deletePermission;
	
	@Column(name = "download_permission")
	private boolean downloadPermission;

	/**
	 * @return the idPermission
	 */
	public Integer getIdPermission() {
		return idPermission;
	}

	/**
	 * @param idPermission the idPermission to set
	 */
	public void setIdPermission(Integer idPermission) {
		this.idPermission = idPermission;
	}

	/**
	 * @return the nomPermission
	 */
	public String getNomPermission() {
		return nomPermission;
	}

	/**
	 * @param nomPermission the nomPermission to set
	 */
	public void setNomPermission(String nomPermission) {
		this.nomPermission = nomPermission;
	}

	/**
	 * @return the createPermission
	 */
	public boolean isCreatePermission() {
		return createPermission;
	}

	/**
	 * @param createPermission the createPermission to set
	 */
	public void setCreatePermission(boolean createPermission) {
		this.createPermission = createPermission;
	}

	/**
	 * @return the readPermission
	 */
	public boolean isReadPermission() {
		return readPermission;
	}

	/**
	 * @param readPermission the readPermission to set
	 */
	public void setReadPermission(boolean readPermission) {
		this.readPermission = readPermission;
	}

	/**
	 * @return the updatePermission
	 */
	public boolean isUpdatePermission() {
		return updatePermission;
	}

	/**
	 * @param updatePermission the updatePermission to set
	 */
	public void setUpdatePermission(boolean updatePermission) {
		this.updatePermission = updatePermission;
	}

	/**
	 * @return the deletePermission
	 */
	public boolean isDeletePermission() {
		return deletePermission;
	}

	/**
	 * @param deletePermission the deletePermission to set
	 */
	public void setDeletePermission(boolean deletePermission) {
		this.deletePermission = deletePermission;
	}

	/**
	 * @return the downloadPermission
	 */
	public boolean isDownloadPermission() {
		return downloadPermission;
	}

	/**
	 * @param downloadPermission the downloadPermission to set
	 */
	public void setDownloadPermission(boolean downloadPermission) {
		this.downloadPermission = downloadPermission;
	}
	
}
