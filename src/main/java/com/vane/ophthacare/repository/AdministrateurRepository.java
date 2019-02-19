package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.Administrateur;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer>{

	Administrateur findByPseudoAdmin(String username);
}
