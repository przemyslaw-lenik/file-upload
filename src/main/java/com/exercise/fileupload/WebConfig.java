package com.exercise.fileupload;

import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WebConfig {

    @Bean
    public com.fasterxml.jackson.databind.Module vavrModule() {
        return new VavrModule();
    }
}
