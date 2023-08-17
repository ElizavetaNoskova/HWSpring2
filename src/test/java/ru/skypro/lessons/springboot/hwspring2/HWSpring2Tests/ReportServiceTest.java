package ru.skypro.lessons.springboot.hwspring2.HWSpring2Tests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.hwspring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.hwspring2.DTO.ReportDTO;
import ru.skypro.lessons.springboot.hwspring2.model.Employee;
import ru.skypro.lessons.springboot.hwspring2.model.Report;
import ru.skypro.lessons.springboot.hwspring2.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.hwspring2.repository.ReportRepository;
import ru.skypro.lessons.springboot.hwspring2.service.EmployeeMapper;
import ru.skypro.lessons.springboot.hwspring2.service.EmployeeService;
import ru.skypro.lessons.springboot.hwspring2.service.EmployeeServiceImpl;
import ru.skypro.lessons.springboot.hwspring2.service.ReportServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private ReportRepository reportRepository;
    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    void ReportTest() throws IOException {
        Integer position = 1;
        Long countEmployees = 1L;
        Integer maxSalary = 100;
        Integer minSalary = 20;
        Double avgSalary = 10.0;
        ReportDTO reportDTO = new ReportDTO(position, countEmployees, maxSalary, minSalary, avgSalary);
        List<ReportDTO> reportDTOS = List.of(reportDTO);
        Integer id = 1;
        String name = "sss";
        Report report = new Report(id, name);

        when(reportRepository.createReport())
                .thenReturn(reportDTOS);
        when(reportRepository.findById(id))
                .thenReturn(Optional.of(report));
        Report actual = reportService.getReportById(id);

        Integer actual1 = reportService.createReport();

        assertEquals(report.getId(), actual.getId());
        assertEquals(report.getId(), actual1);
    }

}