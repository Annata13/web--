package com.example.employee.management.controllers;

import com.example.employee.management.dto.EmployeeDto;
import com.example.employee.management.model.Department;
import com.example.employee.management.model.Employee;
import com.example.employee.management.model.Profession;

import com.example.employee.management.repositories.DepartmentRepository;
import com.example.employee.management.repositories.EmployeeRepository;
import com.example.employee.management.repositories.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = convertToEntity(employeeDto);
        employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(employeeDto.getName());
        employee.setNote(employeeDto.getNote());
        if (employeeDto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow();
            employee.setDepartment(department);
        }
        if (employeeDto.getProfessionId() != null) {
            Profession profession = professionRepository.findById(employeeDto.getProfessionId()).orElseThrow();
            employee.setProfession(profession);
        }
        employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setNote(employee.getNote());
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getName());
        }
        if (employee.getProfession() != null) {
            dto.setProfessionId(employee.getProfession().getId());
            dto.setProfessionName(employee.getProfession().getName());
        }
        return dto;
    }

    private Employee convertToEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setNote(dto.getNote());
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow();
            employee.setDepartment(department);
        }
        if (dto.getProfessionId() != null) {
            Profession profession = professionRepository.findById(dto.getProfessionId()).orElseThrow();
            employee.setProfession(profession);
        }
        return employee;
    }
}
