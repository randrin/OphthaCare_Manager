package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.Maladie;

@Repository
public interface MaladieRepository extends JpaRepository<Maladie, Integer> {

}
