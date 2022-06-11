package com.amorales.vaccination.mappers;

import com.amorales.vaccination.entities.MobilePhone;
import com.amorales.vaccination.pojos.UpdateEmployeeINP;

/**
 * Mapping entity employee phone
 */
public class EmployeePhoneMapper {

    /**
     * Cast Request to entity
     *
     * @param phone Request
     * @return Entity Employee phones
     */
    public static MobilePhone toEntity(UpdateEmployeeINP.PhoneMobile phone){
        return MobilePhone.builder()
                .id(phone.getPhoneId())
                .idEmployee(phone.getEmployee())
                .numberPhone(phone.getNumberPhone())
                .description(phone.getDescription())
                .build();
    }

}
