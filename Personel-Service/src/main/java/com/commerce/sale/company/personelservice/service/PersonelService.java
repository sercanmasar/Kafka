package com.commerce.sale.company.personelservice.service;

import com.commerce.sale.company.personelservice.dto.request.PersonelRequestDto;
import com.commerce.sale.company.personelservice.dto.resource.PersonelResource;
import com.commerce.sale.company.personelservice.mapper.PersonelMapper;
import com.commerce.sale.company.personelservice.repository.PersonelRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonelService {

    private final PersonelRepository personelRepository;
    private final PersonelMapper personelMapper;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "createPersonnel", groupId = "personal-management-service-group")
    public synchronized void save(String personelRequestDto) throws JsonProcessingException {
        personelRepository.save(personelMapper.toEntity(objectMapper.readValue(personelRequestDto, PersonelRequestDto.class)));
    }

    @Transactional(readOnly = true)
    public List<PersonelResource> getAllPersonnel() {
        return personelMapper.toListResource(personelRepository.findAll());
    }
}
