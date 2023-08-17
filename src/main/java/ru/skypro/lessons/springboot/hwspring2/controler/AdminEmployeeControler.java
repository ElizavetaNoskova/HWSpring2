package ru.skypro.lessons.springboot.hwspring2.controler;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.hwspring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.hwspring2.model.Employee;
import ru.skypro.lessons.springboot.hwspring2.service.EmployeeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("admin/employee")
public class AdminEmployeeControler {
    private final EmployeeService employeeService;
    public AdminEmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public List<EmployeeDTO> addNewEmployee(@RequestBody List<EmployeeDTO> employeeDTOS) {
        return employeeService.addEmployee(employeeDTOS);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

}