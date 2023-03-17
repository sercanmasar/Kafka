package com.commerce.sale.company.personelservice.repository;

import com.commerce.sale.company.personelservice.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonelRepository extends JpaRepository<Personnel, Long> {
}
