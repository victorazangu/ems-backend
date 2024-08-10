package io.shemi.spring.ems.service.impl;

import io.shemi.spring.ems.dto.EmployeeDto;
import io.shemi.spring.ems.entity.Employee;
import io.shemi.spring.ems.exception.ResourceNotFound;
import io.shemi.spring.ems.mapper.EmployeeMapper;
import io.shemi.spring.ems.repository.EmployeeRepository;
import io.shemi.spring.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Employee is not exists with the given id :" + id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employees = new ArrayList();
        for (Employee x : employeeRepository.findAll()) {
            employees.add(EmployeeMapper.mapToEmployeeDto(x));
        }
        return employees;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Employee is not exists with the given id :" + id));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);

    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);

    }
}
