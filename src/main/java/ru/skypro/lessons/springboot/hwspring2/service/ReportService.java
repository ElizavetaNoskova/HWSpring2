package ru.skypro.lessons.springboot.hwspring2.service;


import ru.skypro.lessons.springboot.hwspring2.DTO.ReportPathDTO;
import ru.skypro.lessons.springboot.hwspring2.model.Report;
import ru.skypro.lessons.springboot.hwspring2.model.ReportPath;
import java.io.IOException;
import java.util.Optional;

public interface ReportService  {

    Integer createReport() throws IOException;

    Optional<Report> getReportById(int id);

    Integer createReportWithPath() throws IOException;

    Optional<ReportPathDTO> getReportPathById(int id);
}