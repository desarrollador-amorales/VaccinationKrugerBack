package com.amorales.vaccination.mappers;

import com.amorales.vaccination.entities.Auth;

import java.util.UUID;

/**
 * Mapping entity Auth
 */
public class AuthMapper {
    public static Auth toEntity(UUID employeeId, String userName, String password){
        return Auth.builder()
                .idEmployee(employeeId)
                .username(userName)
                .password(password)
                .status(true)
                .build();
    }
}
