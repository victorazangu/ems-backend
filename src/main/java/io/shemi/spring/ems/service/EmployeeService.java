package io.shemi.spring.ems.service;

import io.shemi.spring.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);
    void deleteEmployee(Long id);

}
