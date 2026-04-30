package com.RENTaVAN.app.config;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public JtsModule jtsModule() {
        // Esto registra el módulo que permite convertir JSON a puntos geográficos
        return new JtsModule();
    }
}