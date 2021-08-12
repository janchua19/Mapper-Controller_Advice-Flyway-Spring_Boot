package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.OldEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private OldEmployeeRepository oldEmployeeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeToBeUpdated) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        Employee employeeToDelete = findEmployeeById(employeeId);
        employeeRepository.delete(employeeToDelete);
    }
}
