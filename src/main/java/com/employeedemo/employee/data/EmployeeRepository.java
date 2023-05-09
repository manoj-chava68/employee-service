package com.employeedemo.employee.data;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    EmployeeEntity findByEmail(String email);

    Iterable<EmployeeEntity> findByFirstName(String firstName);

    Iterable<EmployeeEntity> findByFirstNameAndLastName(String firstName,String lastName );

    Iterable<EmployeeEntity> findByLastName(String lastName);

    void deleteByEmail(String email);
}
