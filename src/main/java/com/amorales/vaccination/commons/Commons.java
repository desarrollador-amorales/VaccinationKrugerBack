package com.amorales.vaccination.commons;

import com.amorales.vaccination.exception.GeneralException;
import com.amorales.vaccination.exception.RequestValidationException;
import com.amorales.vaccination.pojos.errors.ApiError;
import com.amorales.vaccination.pojos.errors.ApiFieldError;
import com.amorales.vaccination.utils.IdentifierValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.LinkedList;
import java.util.List;

public class Commons {
    public static void validateFieldRequest(Errors errors) throws RequestValidationException {
        if (errors.hasErrors()) {
            List<String> listError = Commons.messages(errors.getAllErrors());
            throw new RequestValidationException(new ApiFieldError("-1", listError));
        }
    }

    public static void validateIdentifier(String identification) throws GeneralException {
        if(!IdentifierValidator.validateEcuadorianDNI(identification)){
            throw new GeneralException(new ApiError("La cedula ingresada es incorrecta."));
        }
    }

    private static List<String> messages (List<ObjectError> errorList){
        List<String> messageList = new LinkedList<>();
        errorList.forEach(p -> messageList.add(p.getDefaultMessage()));
        return messageList;
    }

    public static String createAleatoryPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

}
