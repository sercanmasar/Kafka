package com.commerce.sale.company.departmentService.dto;

import com.commerce.sale.company.departmentService.enums.SubmitType;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Data
@ToString
public class UserRequestDto {
    @Nullable
    private Long id;
    private String name;
    private String surname;
    private String identity;
    SubmitType type;
}
