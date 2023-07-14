package ru.skypro.lessons.springboot.hwspring2.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoControler {

    @Value("${app.env}")
    String appInfo;

    @GetMapping("/appInfo")
    public String getAppInfo() {
        return appInfo;
    }
}