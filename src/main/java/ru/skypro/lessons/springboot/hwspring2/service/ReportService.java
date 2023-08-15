package ru.skypro.lessons.springboot.hwspring2.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.hwspring2.DTO.ReportDTO;
import ru.skypro.lessons.springboot.hwspring2.model.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ReportService {
    Integer createReport() throws IOException;

    void upload(File file) throws IOException, ClassNotFoundException;

    Report getReportById(int id);
}