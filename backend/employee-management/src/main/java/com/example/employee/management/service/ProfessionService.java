package com.example.employee.management.service;

import com.example.employee.management.model.Profession;
import com.example.employee.management.repositories.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    // Получить все профессии
    public List<Profession> getAllProfessions() {
        return professionRepository.findAll();
    }

    // Получить профессию по ID
    public Optional<Profession> getProfessionById(Long id) {
        return professionRepository.findById(id);
    }

    // Создать новую профессию
    public Profession createProfession(Profession profession) {
        return professionRepository.save(profession);
    }

    // Обновить существующую профессию
    public Profession updateProfession(Long id, Profession professionDetails) {
        // Найти профессию по ID или выбросить исключение, если не найдена
        Profession profession = professionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profession not found"));

        // Обновить поля профессии
        profession.setName(professionDetails.getName());
        profession.setNote(professionDetails.getNote());

        // Сохранить обновленную профессию
        return professionRepository.save(profession);
    }

    // Удалить профессию по ID
    public void deleteProfession(Long id) {
        professionRepository.deleteById(id);
    }
}
