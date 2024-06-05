package com.example.employee.management.service;

import com.example.employee.management.model.Department;
import com.example.employee.management.model.Employee;
import com.example.employee.management.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department updateDepartment(Long id, Department department) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
        existingDepartment.setName(department.getName());
        existingDepartment.setNote(department.getNote());
        existingDepartment.setParentDepartment(department.getParentDepartment());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
