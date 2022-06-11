package com.amorales.vaccination.service;

import com.amorales.vaccination.pojos.AuthINP;

import java.util.Map;

/**
 * Service interface for managing
 * {@link com.amorales.vaccination.entities.Auth}
 */
public interface AuthService {

    /**
     * Save a new employee
     *
     * @param auth the entity data to save
     * @return the persisted entity
     */
    Map<String, Object> autenticate(AuthINP auth) throws Exception;

}
