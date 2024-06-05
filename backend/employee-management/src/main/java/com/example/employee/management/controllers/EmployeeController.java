package com.example.employee.management.controllers;

import com.example.employee.management.dto.EmployeeDto;
import com.example.employee.management.model.Department;
import com.example.employee.management.model.Employee;
import com.example.employee.management.model.Profession;

import com.example.employee.management.repositories.DepartmentRepository;
import com.example.employee.management.repositories.EmployeeRepository;
import com.example.employee.management.repositories.ProfessionRepository;
import com.example.employee.management.service.DepartmentService;
import com.example.employee.management.service.EmployeeService;
import com.example.employee.management.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    private EmployeeService employeeService;

    private DepartmentService departmentService;

    private ProfessionService professionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, ProfessionService professionService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.professionService = professionService;
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }



    @PostMapping
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = convertToEntity(employeeDto);
        employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.getEmployeeById(id).orElseThrow();
        employee.setName(employeeDto.getName());
        employee.setNote(employeeDto.getNote());
        if (employeeDto.getDepartmentId() != null) {
            Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId()).orElseThrow();
            employee.setDepartment(department);
        }
        if (employeeDto.getProfessionId() != null) {
            Profession profession = professionService.getProfessionById(employeeDto.getProfessionId()).orElseThrow();
            employee.setProfession(profession);
        }
        employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
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
            Department department = departmentService.getDepartmentById(dto.getDepartmentId()).orElseThrow();
            employee.setDepartment(department);
        }
        if (dto.getProfessionId() != null) {
            Profession profession = professionService.getProfessionById(dto.getProfessionId()).orElseThrow();
            employee.setProfession(profession);
        }
        return employee;
    }
}
