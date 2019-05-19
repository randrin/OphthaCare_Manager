package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.ProfessionMedecin;

@Repository
public interface ProfessionMedecinRepository extends JpaRepository<ProfessionMedecin, Integer> {

}
