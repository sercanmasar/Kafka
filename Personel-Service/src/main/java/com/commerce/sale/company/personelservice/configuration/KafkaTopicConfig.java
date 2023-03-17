package com.commerce.sale.company.personelservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;

@EnableKafka
@Configuration
class KafkaTopicConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("createPersonnel").build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("deletePersonnel").build();
    }
}
