package ru.skypro.lessons.springboot.hwspring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.hwspring2.model.ReportPath;

public interface ReportPathRepository extends JpaRepository<ReportPath, Integer> {


}