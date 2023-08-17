package ru.skypro.lessons.springboot.hwspring2.controler;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.hwspring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.hwspring2.service.EmployeeService;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/employee")
public class EmployeeControler {
    private final EmployeeService employeeService;
    public EmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/salary/sum")
    public double getSalarySum() {
        return employeeService.salarySum();
    }
    @GetMapping("/salary/min")
    public EmployeeDTO getSalaryMin() {
        return employeeService.minSalary();
    }
    @GetMapping("/salary/max")
    public EmployeeDTO getSalaryMax() {
        return employeeService.maxSalary();
    }

    @GetMapping("/salary/high-salary")
    public Integer getEmployeeHighSalary() {
        return employeeService.employeeHighSalary();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/all-employee-new")
    public List<EmployeeDTO> all() {
        return employeeService.getAllNew();
    }
    @GetMapping("salaryHigherThan")
    public List<EmployeeDTO> salaryHigherThan(@RequestParam("salary") Integer compareSalary) {
        return employeeService.salaryHigherThan(compareSalary);
    }
    @GetMapping("withHighestSalary")
    public List<EmployeeDTO> salaryWithHighestSalary() {
        return employeeService.withHighestSalary();
    }
    @GetMapping
    public List<EmployeeDTO> getEmployeesForPosition(@RequestParam(required = false) String position) {
        return employeeService.getEmployee(
                Optional.ofNullable(position)
                        .filter(p -> !p.isEmpty())
                        .orElse(null));
    }
    @GetMapping("/{id}/fullInfo")
    public EmployeeDTO getEmployeeFullInfo(@PathVariable int id) {
        return employeeService.getEmployeeFullInfo(id);
    }
    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeesFromPage(@RequestParam(required = false, defaultValue = "0")  int page) {
        return employeeService.getEmployeesFromPage(page);
    }
}