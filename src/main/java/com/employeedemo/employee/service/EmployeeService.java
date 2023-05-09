package com.employeedemo.employee.service;

import com.employeedemo.employee.shared.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeById(long id);

    EmployeeDto getEmployeeByEmail(String email);

    boolean deleteEmployeeByEmail(String email);

    List<EmployeeDto> getEmployeesByFirstNameAndLastName(String firstName, String lastName);

    List<EmployeeDto> getEmployeesByLastName(String lastName);

    List<EmployeeDto> getEmployeesByFirstName(String firstName);

    EmployeeDto createEmployee(EmployeeDto employeeDetails);
    List<EmployeeDto> getAllEmployees();
}
