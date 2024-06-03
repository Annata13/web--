package com.example.employee.management.repositories;

import com.example.employee.management.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
