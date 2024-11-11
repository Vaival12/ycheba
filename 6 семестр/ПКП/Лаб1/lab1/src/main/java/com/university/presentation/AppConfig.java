package com.university.presentation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.university"})
public class AppConfig {
    public AppConfig() {
    }
}
