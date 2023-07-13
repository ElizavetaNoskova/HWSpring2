package ru.skypro.lessons.springboot.hwspring2.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.hwspring2.model.Employee;
import ru.skypro.lessons.springboot.hwspring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.hwspring2.repository.EmployeeRepository;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.print.Pageable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
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
        logger.debug("Метод подсчёта суммы зарплат сотрудников.");
        return employeeRepository.getSalarySum();
    }

    @Override
    public Optional<Integer> getMinSalary() {
        logger.debug("Метод поиска минимальной зарплаты среди сотрудников.");
        return employeeRepository.getMinSalary();
    }

    @Override
    public Optional<Integer> getMaxSalary() {
        logger.debug("Метод поиска максимальной зарплаты среди сотрудников.");
        return employeeRepository.getMaxSalary();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithSalaryHigherThenAvg() {
        logger.debug("Метод поиска средней зарплаты среди сотрудников.");
        return employeeRepository.getAllEmployeesWithSalaryHigherThenAvg().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee(Employee employee) {
        logger.debug("Метод добавления сотрудников.");
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> getEmployeeById(Integer id) {
        logger.debug("Метод поиска сотрудника с {} id", id);
        List<EmployeeDTO>
                optionalEmployeeDTO =
                employeeRepository.findById(id).stream()
                        .map(EmployeeDTO::fromEmployee)
                        .collect(Collectors.toList());

        return optionalEmployeeDTO;
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        logger.debug("Метод удаления сотрудника с {} id", id);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithSalaryHigherThan(int salary) {
        logger.debug("Метод сортировки сотрудников с зарплатой выше {}", salary);
        return employeeRepository.getAllEmployeesWithSalaryHigherThan(salary).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithMatchingPosition(String position) {
        logger.debug("Метод поиска сотрудников на позиции {}", position);
        return employeeRepository.getAllEmployeesWithMatchingPosition(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeFullInfo(int id) {
        logger.debug("Метод получения полной информации о сотруднике по id {}", id);
        return employeeRepository.findById(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());

    }

    @Override
    public List<EmployeeDTO> getEmployeesInPageFormat(int page) {
        logger.debug("Метод получения cписка сотрудников на странице {}", page);
        return employeeRepository.findAll(PageRequest.of(page, 10)).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void saveEmployeeFromJson(MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<Employee> employees = objectMapper.readValue(file.getBytes(),new TypeReference<>(){});
        employeeRepository.saveAll(employees);
    }
}