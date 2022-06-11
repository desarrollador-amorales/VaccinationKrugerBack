package com.amorales.vaccination.mappers;

import com.amorales.vaccination.entities.VaccinationEmployee;
import com.amorales.vaccination.pojos.UpdateEmployeeINP;

/**
 * Mapping entity employee VaccinationEmployee
 */
public class EmployeeVaccinationMapper {
    /**
     * Cast Request to entity
     *
     * @param vaccination Request
     * @return Entity Employee VaccinationEmployee
     */
    public static VaccinationEmployee toEntity(UpdateEmployeeINP.VaccinationEmployee vaccination){
        return VaccinationEmployee.builder()
                .id(vaccination.getVaccinationId())
                .idEmployee(vaccination.getEmployee())
                .idVaccinationType(vaccination.getVaccineType())
                .vaccinationDate(vaccination.getVaccinationDate())
                .numberDoses(vaccination.getNumberDoses())
                .build();
    }
}
