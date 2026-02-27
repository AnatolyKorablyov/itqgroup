package com.itqgroup.service.app.configuration;

import com.itqgroup.service.api.ApprovedStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ConcurrencyConfiguration {

    @Bean
    public Map<String, List<Map<String, ApprovedStatus>>> concurrencyThreadsStatuses() {
        return new ConcurrentHashMap<>();
    }
}
