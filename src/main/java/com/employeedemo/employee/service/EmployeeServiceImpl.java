package com.employeedemo.employee.service;

import com.employeedemo.employee.data.EmployeeEntity;
import com.employeedemo.employee.data.EmployeeRepository;
import com.employeedemo.employee.shared.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
 public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> allEmployees = new ArrayList<>();
        Iterable<EmployeeEntity> employees = employeeRepository.findAll();
        return getEmployeeDtos(allEmployees, employees);

    }

    private List<EmployeeDto> getEmployeeDtos(List<EmployeeDto> allEmployees, Iterable<EmployeeEntity> employees) {
        ModelMapper modelMapper = new ModelMapper();
        long count = StreamSupport.stream(employees.spliterator(), false).count();
        if(count > 0){
            employees.forEach(employeeEntity -> {
                EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
                allEmployees.add(employeeDto);
            });
        }

        return allEmployees;
    }

    @Override
    public EmployeeDto getEmployeeById(long id){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return new ModelMapper().map(employeeEntity, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        EmployeeEntity employeeEntity = employeeRepository.findByEmail(email);
        if(employeeEntity == null){
            return null;
        }else{
            return new ModelMapper().map(employeeEntity, EmployeeDto.class);
        }
    }

    @Override
    public boolean deleteEmployeeByEmail(String email) {
        try{
            employeeRepository.deleteByEmail(email);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public List<EmployeeDto> getEmployeesByFirstNameAndLastName(String firstName, String lastName) {
        List<EmployeeDto> allEmployees = new ArrayList<>();
        Iterable<EmployeeEntity> employees = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        return getEmployeeDtos(allEmployees, employees);
    }

    @Override
    public List<EmployeeDto> getEmployeesByLastName(String lastName) {
        List<EmployeeDto> allEmployees = new ArrayList<>();
        Iterable<EmployeeEntity> employees = employeeRepository.findByLastName(lastName);
        return getEmployeeDtos(allEmployees, employees);
    }

    @Override
    public List<EmployeeDto> getEmployeesByFirstName(String firstName) {
        List<EmployeeDto> allEmployees = new ArrayList<>();
        Iterable<EmployeeEntity> employees = employeeRepository.findByFirstName(firstName);
        return getEmployeeDtos(allEmployees, employees);
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeEntity employeeEntity =modelMapper.map(employeeDetails, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }
}
