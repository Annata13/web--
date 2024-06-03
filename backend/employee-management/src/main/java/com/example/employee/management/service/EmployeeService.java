package com.example.employee.management.service;

import com.example.employee.management.model.Department;
import com.example.employee.management.model.Employee;
import com.example.employee.management.model.Profession;
import com.example.employee.management.repositories.DepartmentRepository;
import com.example.employee.management.repositories.EmployeeRepository;
import com.example.employee.management.repositories.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    // Получить всех сотрудников
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Получить сотрудника по ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Создать нового сотрудника

    public Employee createEmployee(Employee employee) {
        Optional<Profession> profession = professionRepository.findById(employee.getProfession().getId());
        Optional<Department> department = departmentRepository.findById(employee.getDepartment().getId());
        if (!profession.isPresent() || !department.isPresent()) {
            throw new RuntimeException("Profession or Department not found");
        }

        // Установка профессии и отдела для сотрудника
        employee.setProfession(profession.get());
        employee.setDepartment(department.get());

        return employeeRepository.save(employee);
    }


    // Обновить существующего сотрудника
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setNote(employee.getNote());
        Optional<Profession> profession = professionRepository.findById(employee.getProfession().getId());
        Optional<Department> department = departmentRepository.findById(employee.getDepartment().getId());
        profession.ifPresent(existingEmployee::setProfession);
        department.ifPresent(existingEmployee::setDepartment);
        return employeeRepository.save(existingEmployee);
    }

    // Удалить сотрудника по ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
