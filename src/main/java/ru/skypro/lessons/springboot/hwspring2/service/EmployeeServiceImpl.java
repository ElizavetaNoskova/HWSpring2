package ru.skypro.lessons.springboot.hwspring2.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.homework_spring2.repository.EmployeeRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.getAllEmployees().stream().
                map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getSalarySum() {
        return employeeRepository.getSalarySum();
    }

    @Override
    public Optional<Integer> getMinSalary() {
        return employeeRepository.getMinSalary();
    }

    @Override
    public Optional<Integer> getMaxSalary() {
        return employeeRepository.getMaxSalary();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithSalaryHigherThenAvg() {
        return employeeRepository.getAllEmployeesWithSalaryHigherThenAvg().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> getEmployeeById(Integer id) {
        List<EmployeeDTO>
                optionalEmployeeDTO =
                employeeRepository.findById(id).stream()
                        .map(EmployeeDTO::fromEmployee)
                        .collect(Collectors.toList());

        return optionalEmployeeDTO;
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithSalaryHigherThan(int salary) {
        return employeeRepository.getAllEmployeesWithSalaryHigherThan(salary).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithMatchingPosition(String position) {
        return employeeRepository.getAllEmployeesWithMatchingPosition(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeFullInfo(int id) {
        return employeeRepository.findById(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());

    }

    @Override
    public List<EmployeeDTO> getEmployeesInPageFormat(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 10)).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

}