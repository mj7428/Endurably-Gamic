package com.gamic.Endurably.Gamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamic.Endurably.Gamic.Entity.FieldValue;

@Repository
public interface FieldValueRepository extends JpaRepository<FieldValue, Long> {
}
