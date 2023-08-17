package ru.skypro.lessons.springboot.hwspring2.service;

import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.hwspring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.hwspring2.model.Employee;
import ru.skypro.lessons.springboot.hwspring2.model.Position;

import java.util.Optional;

@Component
public class EmployeeMapper {
    public EmployeeDTO toDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setPosition(Optional.ofNullable(employee.getPosition())
                .map(Position::getPosition)
                .orElse(null));
        return employeeDTO;
    }


    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        return employee;
    }
}