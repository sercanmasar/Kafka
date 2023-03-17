package com.commerce.sale.company.personelservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@RequiredArgsConstructor
@AllArgsConstructor
public class Personnel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String identity;
    SubmitType type;
}
