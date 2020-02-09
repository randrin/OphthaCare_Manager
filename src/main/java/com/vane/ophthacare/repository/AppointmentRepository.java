package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.Appointment;	

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
