package com.commerce.sale.company.departmentService.service;

import com.commerce.sale.company.departmentService.dto.UserRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final KafkaTemplate kafkaTemplate;

    private final ObjectMapper objectMapper;

    public void saveUser(UserRequestDto userRequestDto) throws JsonProcessingException {
        kafkaTemplate.send("createPersonnel", objectMapper.writeValueAsString(userRequestDto));
    }
}
