package com.amorales.vaccination.service.tools;

import com.amorales.vaccination.entities.*;
import com.amorales.vaccination.mappers.AuthMapper;
import com.amorales.vaccination.mappers.EmployeeAddressMapper;
import com.amorales.vaccination.mappers.EmployeePhoneMapper;
import com.amorales.vaccination.mappers.EmployeeVaccinationMapper;
import com.amorales.vaccination.pojos.UpdateEmployeeINP;
import com.amorales.vaccination.repositories.RolesRepository;
import com.amorales.vaccination.repositories.VaccineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Work with employee data
 */
@Service
public class EmployeeTool extends Tools {
    private final String ROL_EMPLOYEE = "ROLE_USER";
    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;
    @Autowired
    private RolesRepository rolesRepository;

    /**
     * Generate and validate data Employee phone to save
     *
     * @param employee                 Owner Employee
     * @param employeeINP                   List of Employee phone
     * @return New phone list
     */
    protected Set<MobilePhone> createEmployeePhone(Employee employee, UpdateEmployeeINP employeeINP) {

        Set<MobilePhone> mobilePhones = new HashSet<>();

        if(employeeINP.getPhones() != null){
            mobilePhones = employeeINP.getPhones().stream()
                    .peek(e -> e.setEmployee(employee.getId()))
                    .map(EmployeePhoneMapper::toEntity).collect(Collectors.toSet());
        }

        Set<MobilePhone> newPhones = employee.getMobilePhones();

        newPhones.addAll(mobilePhones);

        if (newPhones.stream().collect(Collectors.groupingBy(p -> Arrays.asList(p.getNumberPhone()),
                Collectors.counting())).values().stream().anyMatch(i -> i > 1)) {
            throw generateErrorEmployee("Este numero celular ya se encuentra registrado para este empleado.");
        }

        return newPhones;
    }

    /**
     * Generate and validate data Employee Address to save
     *
     * @param employee                 Owner Employee
     * @param employeeINP                   List of Employee Address
     * @return New Address list
     */
    protected Set<Address> createEmployeeAddress(Employee employee, UpdateEmployeeINP employeeINP) {
        Set<Address> addresses = new HashSet<>();

        if(employeeINP.getAddresses() != null){
            addresses = employeeINP.getAddresses().stream()
                    .peek(e -> e.setEmployee(employee.getId()))
                    .map(EmployeeAddressMapper::toEntity).collect(Collectors.toSet());
        }

        Set<Address> newAddress = employee.getAddresses();
        newAddress.addAll(addresses);

        if(newAddress.isEmpty()){
            throw generateErrorEmployee("Debe contener al menos una dirección");
        }

        if (newAddress.stream().collect(Collectors.groupingBy(p -> Arrays.asList(p.getOneStreet(), p.getTwoStreet(), p.getHouseNumber()),
                Collectors.counting())).values().stream().anyMatch(i -> i > 1)) {
            throw generateErrorEmployee("Una de las direcciones ya se encuentra registrado para este empleado.");
        }
        return newAddress;
    }

    /**
     * Generate and validate data Employee Address to save
     *
     * @param employee                 Owner Employee
     * @param employeeINP                   List of Employee Vaccination
     * @return New VaccinationEmployee list
     */
    protected Set<VaccinationEmployee> createEmployeeVaccination(Employee employee, UpdateEmployeeINP employeeINP) {

        Set<VaccinationEmployee> vaccinationEmployees = new HashSet<>();

        if(employeeINP.getVaccinationEmployees() != null){
            vaccinationEmployees = employeeINP.getVaccinationEmployees().stream()
                    .peek(e -> e.setEmployee(employee.getId()))
                    .map(EmployeeVaccinationMapper::toEntity).collect(Collectors.toSet());
        }

        getVaccineById(vaccinationEmployees);
        Set<VaccinationEmployee> vaccination = employee.getVaccinationEmployees();
        vaccination.addAll(vaccinationEmployees);

        if(vaccination.isEmpty()){
            throw generateErrorEmployee("Si esta vacunado debe seleccionar una vacuna");
        }

        if (vaccination.stream().collect(Collectors.groupingBy(p -> Arrays.asList(p.getIdVaccinationType()),
                Collectors.counting())).values().stream().anyMatch(i -> i > 1)) {
            throw generateErrorEmployee("Ya tiene un registro de ese tipo de vacuna");
        }

        return vaccination;
    }

    protected Set<Auth> createAuth(Employee employee, String password) {
        Auth auth = AuthMapper.toEntity(employee.getId(), employee.getIdentification(), password);
        Role role = rolesRepository.findByName(ROL_EMPLOYEE).orElseThrow(this::generateErrorNotFoundRole);
        auth.setRoles(Collections.singleton(role));
        Set<Auth> auths = new HashSet<>();
        auths.add(auth);
        return auths;
    }

    private void getVaccineById(Set<VaccinationEmployee> vaccinations){
        vaccinations.forEach(e -> vaccineTypeRepository.findById(e.getIdVaccinationType()).orElseThrow(this::generateErrorNotFoundVaccinateType));
    }

}
