package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
