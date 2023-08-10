package ru.skypro.lessons.springboot.hwspring2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.hwspring2.DTO.ReportDTO;
import ru.skypro.lessons.springboot.hwspring2.DTO.ReportPathDTO;
import ru.skypro.lessons.springboot.hwspring2.model.Report;
import ru.skypro.lessons.springboot.hwspring2.model.ReportPath;
import ru.skypro.lessons.springboot.hwspring2.repository.ReportPathRepository;
import ru.skypro.lessons.springboot.hwspring2.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportPathRepository reportPathRepository;
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportRepository reportRepository, ReportPathRepository reportPathRepository) {
        this.reportRepository = reportRepository;
        this.reportPathRepository = reportPathRepository;
    }

    @Override
    public Integer createReport() throws IOException {
        logger.debug("Метод создания Report");
        Report report = new Report();
        report.setId(1);
        report.setData(String.valueOf((reportRepository.createReport())));

        reportRepository.save(report);

        return report.getId();

    }

    @Override
    public void upload(File file) throws IOException, ClassNotFoundException {
        logger.debug("Метод загрузки сотрудников из файла.");
    }

    @Override
    public Optional<Report> getReportById(int id) {
        logger.debug("Метод поиска репорта по id {}", id);
        return reportRepository.findById(id);
    }

    @Override
    public Integer createReportWithPath() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = String.valueOf(reportRepository.createReport());
        objectMapper.writeValue(new File("rep.json"), json);
        ReportPath reportPath = new ReportPath();
        Path path = Paths.get(("rep.json"));

//        String json = String.valueOf(reportRepository.createReport());
        Resource resource = new ByteArrayResource(json.getBytes());
        reportPath.setFilePath(String.valueOf(path.toAbsolutePath()));
        reportPathRepository.save(reportPath);
        return reportPath.getId();
    }

    @Override
    public Optional<ReportPathDTO> getReportPathById(int id) {

        return Optional.of(ReportPathDTO.fromReportPath(reportPathRepository.getReferenceById(id)));
    }
}