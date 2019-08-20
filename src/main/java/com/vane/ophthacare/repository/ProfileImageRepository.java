package com.vane.ophthacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vane.ophthacare.model.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer> {

}
