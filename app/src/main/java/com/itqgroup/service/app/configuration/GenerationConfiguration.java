package com.itqgroup.service.app.configuration;

import com.itqgroup.service.app.utils.GenerationDocumentsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GenerationConfiguration {

    @Value("${application.generation.document.value}")
    private int GENERATION_DOCUMENT_VALUE;

    @Value("${application.service.url}")
    private String SERVICE_URL;

    @Bean
    public GenerationDocumentsUtil generationDocumentsUtil() {
        log.info("GENERATE DOCUMENTS: {}", GENERATION_DOCUMENT_VALUE);
        return new GenerationDocumentsUtil(SERVICE_URL, GENERATION_DOCUMENT_VALUE);
    }
}
