package ru.skypro.lessons.springboot.hwspring2.service;


import ru.skypro.lessons.springboot.hwspring2.DTO.ReportPathDTO;
import ru.skypro.lessons.springboot.hwspring2.model.Report;
import ru.skypro.lessons.springboot.hwspring2.model.ReportPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface ReportService {

    Integer createReport() throws IOException;

    void upload(File file) throws IOException, ClassNotFoundException;

    Optional<Report> getReportById(int id);

    Integer createReportWithPath() throws IOException;

    Optional<ReportPathDTO> getReportPathById(int id);
}