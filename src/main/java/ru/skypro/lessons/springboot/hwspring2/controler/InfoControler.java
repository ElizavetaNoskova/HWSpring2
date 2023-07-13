package ru.skypro.lessons.springboot.hwspring2.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoControler {
    @Value("${app.env}")
    String env;
    @GetMapping("/appInfo")
    public String appInfo(){
        return env;
    };
}