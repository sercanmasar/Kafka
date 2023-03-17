package com.commerce.sale.company.personelservice.dto.resource;

import com.commerce.sale.company.personelservice.enums.SubmitType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Data
@ToString
@Builder
public class PersonelResource {
    @Nullable
    private Long id;
    private String name;
    private String surname;
    private String identity;
    SubmitType type;
}
