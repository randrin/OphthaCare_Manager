package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.Administrateur;
import com.vane.ophthacare.model.Medecin;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

	Medecin findByMatriculeMedecin(String matricule);
}
