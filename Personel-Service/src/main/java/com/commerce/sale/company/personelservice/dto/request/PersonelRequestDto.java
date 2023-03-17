package com.commerce.sale.company.personelservice.dto.request;

import com.commerce.sale.company.personelservice.entity.SubmitType;

public record PersonelRequestDto(
         Long id,
         String name,
         String surname,
         String identity,
         SubmitType type
) {
}
