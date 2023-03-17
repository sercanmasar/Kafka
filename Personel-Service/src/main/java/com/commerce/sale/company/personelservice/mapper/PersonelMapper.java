package com.commerce.sale.company.personelservice.mapper;

import com.commerce.sale.company.personelservice.dto.request.PersonelRequestDto;
import com.commerce.sale.company.personelservice.dto.resource.PersonelResource;
import com.commerce.sale.company.personelservice.entity.Personnel;
import com.commerce.sale.company.personelservice.enums.SubmitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonelMapper {
   public Personnel toEntity(PersonelRequestDto personelRequestDto){
        return new Personnel(
                null,
                personelRequestDto.name(),
                personelRequestDto.surname(),
                personelRequestDto.identity(),
                personelRequestDto.type()
        );
    }
    public List<PersonelResource> toListResource(List<Personnel> personnelList){
       return personnelList.stream().map(personnel ->
               PersonelResource.builder()
                       .id(personnel.getId())
                       .name(personnel.getName())
                       .surname(personnel.getSurname())
                       .identity(personnel.getIdentity())
                       .type(SubmitType.SUBMIT).build()
               ).collect(Collectors.toList());
    }
}
