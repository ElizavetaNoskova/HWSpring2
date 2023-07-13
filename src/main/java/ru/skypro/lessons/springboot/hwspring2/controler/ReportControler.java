package ru.skypro.lessons.springboot.hwspring2.controler;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.hwspring2.DTO.ReportDTO;
import ru.skypro.lessons.springboot.hwspring2.service.ReportService;
import ru.skypro.lessons.springboot.hwspring2.service.EmployeeService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportControler {

    private final ReportService reportService;

    public ReportControler(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("admin/report")
    public Integer createReport() throws IOException {
        return reportService.createReport();
    }

    @PostMapping(value = "admin/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException, ClassNotFoundException {
        File file = new File(multipartFile.getName());
        Files.write(file.toPath(), multipartFile.getBytes());
        reportService.upload(file);
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