package com.amorales.vaccination.service.impl;

import com.amorales.vaccination.entities.Auth;
import com.amorales.vaccination.exception.AuthException;
import com.amorales.vaccination.pojos.AuthINP;
import com.amorales.vaccination.pojos.errors.ApiError;
import com.amorales.vaccination.repositories.AuthRepository;
import com.amorales.vaccination.security.JwtTokenProvider;
import com.amorales.vaccination.service.AuthService;
import com.amorales.vaccination.service.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Service implementation for managing {@link Auth}
 */
@Slf4j
@Service
public class AuthServiceImpl extends Tools implements AuthService {

    @Autowired
    private AuthenticationManager authenticateManager;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<String,Object> autenticate(AuthINP auth) throws Exception {
        SecurityContextHolder.getContext().setAuthentication(this.authenticateUser(auth));

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generateToken(auth.getUsername(), 1200);

        Map<String,Object> res = new LinkedHashMap<>();
        res.put("token", token);
        return res;
    }

    private Authentication authenticateUser(AuthINP auth) throws AuthException {
        try {
            Auth authOpt = authRepository.findByUsername(auth.getUsername())
                                            .orElseThrow(this::generateErrorNotFoundAuth);
			if(!authOpt.getStatus()) {
				throw new AuthException(new ApiError("Tu usuario no se encuentra disponible para transaccionar"));
			}
            return authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        }catch(BadCredentialsException e) {
            throw new AuthException(new ApiError("El usuario y/o contraseña son incorrectos"));
        }catch(UsernameNotFoundException user) {
            throw new AuthException(new ApiError("El usuario y/o contraseña son incorrectos"));
        }

    }

}
