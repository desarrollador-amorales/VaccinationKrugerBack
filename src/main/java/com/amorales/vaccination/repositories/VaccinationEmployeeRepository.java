package com.amorales.vaccination.repositories;

import com.amorales.vaccination.entities.VaccinationEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Spring data repository for the {@link VaccinationEmployee} entity
 */
@Repository
public interface VaccinationEmployeeRepository extends JpaRepository<VaccinationEmployee, Serializable> {
}
