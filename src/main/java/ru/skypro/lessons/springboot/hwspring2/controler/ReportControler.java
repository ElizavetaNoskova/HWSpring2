package ru.skypro.lessons.springboot.hwspring2.controler;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.hwspring2.DTO.ReportDTO;
import ru.skypro.lessons.springboot.hwspring2.service.ReportService;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportControler {

    private final ReportService reportService;

    public ReportControler(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/")
    public Integer createReport() throws IOException {

        return reportService.createReport();
    }

    @GetMapping(value = "/report/{id}")
    public ResponseEntity<Resource> getReportById(@PathVariable int id) {

        String jsonFile = "report.json";
        String json = String.valueOf(reportService.getReportById(id));
        Resource resource = new ByteArrayResource(json.getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + jsonFile + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }


    @PostMapping(value = "/reportWithPath")
    public Integer createReportPath() throws IOException {

        return reportService.createReportWithPath();
    }

    @GetMapping(value = "/reportPath/{id}")
    public ResponseEntity<Resource> getReportPathById(@PathVariable int id) throws FileNotFoundException {

        String fileReport = "report.json";
        String pathToReport = reportService.getReportPathById(id).get().getPath();
        File file = new File(pathToReport);
        Resource resource = new PathResource(file.getPath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileReport + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}