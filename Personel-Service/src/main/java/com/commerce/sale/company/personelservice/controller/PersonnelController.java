package com.commerce.sale.company.personelservice.controller;

import com.commerce.sale.company.personelservice.dto.resource.PersonelResource;
import com.commerce.sale.company.personelservice.service.PersonelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnel/")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonelService personelService;

    @PostMapping
    public ResponseEntity<Void> savePersonel(@RequestBody String personelRequestDto) throws JsonProcessingException {
        personelService.save(personelRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PersonelResource>> getAllPersonnel() {
        return ResponseEntity.ok().body(personelService.getAllPersonnel());
    }
}
