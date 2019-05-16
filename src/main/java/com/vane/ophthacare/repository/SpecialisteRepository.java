package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.Specialiste;

@Repository
public interface SpecialisteRepository extends JpaRepository<Specialiste, Integer> {

}
