package com.bao.practice.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bao.kafka")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaConfigProps {
    private String topic;
}
