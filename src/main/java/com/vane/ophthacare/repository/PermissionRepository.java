package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vane.ophthacare.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{

}
