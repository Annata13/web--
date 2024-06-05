package com.example.employee.management.controllers;

import com.example.employee.management.dto.ProfessionDto;
import com.example.employee.management.model.Profession;
import com.example.employee.management.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/professions")
public class ProfessionController {

    private ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    // Получить список всех профессий
    @GetMapping
    public List<ProfessionDto> getAllProfessions() {
        List<Profession> professions = professionService.getAllProfessions();
        return professions.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    // Получить профессию по ID
    @GetMapping("/{id}")
    public Optional<ProfessionDto> getProfessionById(@PathVariable Long id) {
        return professionService.getProfessionById(id).map(this::convertToDto);
    }

    // Создать новую профессию
    @PostMapping
    public ProfessionDto createProfession(@RequestBody ProfessionDto professionDto) {
        Profession profession = convertToEntity(professionDto);
        professionService.createProfession(profession);
        return convertToDto(profession);
    }

    // Обновить существующую профессию
    @PutMapping("/{id}")
    public ProfessionDto updateProfession(@PathVariable Long id, @RequestBody ProfessionDto professionDetails) {
        Profession profession = convertToEntity(professionDetails);
        professionService.updateProfession(id, profession);
        return convertToDto(profession);
    }

    // Удалить профессию по ID
    @DeleteMapping("/{id}")
    public void deleteProfession(@PathVariable Long id) {
        professionService.deleteProfession(id);
    }

    private ProfessionDto convertToDto(Profession profession) {
        ProfessionDto dto = new ProfessionDto();
        dto.setId(profession.getId());
        dto.setName(profession.getName());
        dto.setNote(profession.getNote());
        return dto;
    }

    private Profession convertToEntity(ProfessionDto dto) {
        Profession profession = new Profession();
        profession.setName(dto.getName());
        profession.setNote(dto.getNote());
        return profession;
    }
}
