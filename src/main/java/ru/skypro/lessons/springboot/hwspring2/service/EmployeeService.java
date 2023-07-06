package ru.skypro.lessons.springboot.hwspring2.service;

import ru.skypro.lessons.springboot.hwspring2.model.Employee;
import ru.skypro.lessons.springboot.hwspring2.DTO.EmployeeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.io.IOException;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    Integer getSalarySum();

    Optional<Integer> getMinSalary();

    Optional<Integer> getMaxSalary();

    List<EmployeeDTO> getAllEmployeesWithSalaryHigherThenAvg();

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    List<EmployeeDTO> getEmployeeById(Integer id);

    void deleteEmployeeById(Integer id);

    List<EmployeeDTO> getAllEmployeesWithSalaryHigherThan(int salary);

    List<EmployeeDTO> getAllEmployeesWithMatchingPosition(String position);

    List<EmployeeDTO> getEmployeeFullInfo(int id);

    List<EmployeeDTO> getEmployeesInPageFormat(int page);
    void saveEmployeeFromJson(MultipartFile file) throws IOException;
}