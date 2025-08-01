package com.gamic.Endurably.Gamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamic.Endurably.Gamic.Entity.RegistrationField;

@Repository
public interface RegistrationFieldRepository extends JpaRepository<RegistrationField, Long> {
}
