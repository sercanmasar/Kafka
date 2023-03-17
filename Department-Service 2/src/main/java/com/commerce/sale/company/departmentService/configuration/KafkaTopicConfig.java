package com.commerce.sale.company.departmentService.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
@EnableKafka
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("createPersonnel").build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("deletePersonnel").build();
    }
}
