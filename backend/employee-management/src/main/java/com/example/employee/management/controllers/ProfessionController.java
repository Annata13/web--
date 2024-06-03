package com.example.employee.management.controllers;

import com.example.employee.management.model.Profession;
import com.example.employee.management.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professions")
public class ProfessionController {
    @Autowired
    private ProfessionService professionService;

    // Получить список всех профессий
    @GetMapping
    public List<Profession> getAllProfessions() {
        return professionService.getAllProfessions();
    }

    // Получить профессию по ID
    @GetMapping("/{id}")
    public Optional<Profession> getProfessionById(@PathVariable Long id) {
        return professionService.getProfessionById(id);
    }

    // Создать новую профессию
    @PostMapping
    public Profession createProfession(@RequestBody Profession profession) {
        return professionService.createProfession(profession);
    }

    // Обновить существующую профессию
    @PutMapping("/{id}")
    public Profession updateProfession(@PathVariable Long id, @RequestBody Profession professionDetails) {
        return professionService.updateProfession(id, professionDetails);
    }

    // Удалить профессию по ID
    @DeleteMapping("/{id}")
    public void deleteProfession(@PathVariable Long id) {
        professionService.deleteProfession(id);
    }
}
