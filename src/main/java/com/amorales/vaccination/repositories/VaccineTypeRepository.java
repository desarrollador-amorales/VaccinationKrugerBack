package com.amorales.vaccination.repositories;

import com.amorales.vaccination.entities.VaccinationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccinationType, Serializable> {
    Optional<VaccinationType> findById(UUID id);
}