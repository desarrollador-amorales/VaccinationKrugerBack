package com.amorales.vaccination.repositories;

import com.amorales.vaccination.entities.Employee;
import com.amorales.vaccination.entities.enums.VaccinationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Spring data repository for the {@link com.amorales.vaccination.entities.Employee} entity
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {

    /**
     * Get employee by identification
     *
     * @param identification Owner employee identification
     * @return {@link Employee}
     */
    Optional<Employee> findByIdentificationAndStatus(String identification, Boolean status);

    /**
     * Get Employee by id
     *
     * @param id Owner Employee id
     * @return Employee
     */
    Optional<Employee> findByIdAndStatus(UUID id, Boolean status);

    /**
     * Get Employee by id
     *
     * @return Employee
     */
    Set<Employee> findAllByOrderBySurnameAsc();

    Set<Employee> findByVaccinationStatusOrderBySurnameAsc(VaccinationStatus vaccinationStatus);

    @Query("SELECT ve.employee FROM VaccinationEmployee ve" +
            "   WHERE ve.idVaccinationType = :VACCINATE_ID")
    Set<Employee> getByVaccinateType(@Param("VACCINATE_ID") UUID vaccinateId);

    @Query("SELECT ve.employee FROM VaccinationEmployee ve" +
            "   WHERE ve.vaccinationDate BETWEEN :FROM_DATE AND :TO_DATE")
    Set<Employee> getByRangeDate(@Param("FROM_DATE") Date fromDate, @Param("TO_DATE") Date toDate);
}
