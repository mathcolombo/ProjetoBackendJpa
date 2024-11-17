package com.unisales.projetobackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Método para configurar a forma que o spring vai trabalhar com a data
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        registrar.registerFormatters(registry);
    }
}