package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAllEmployees() {

        return employeeRepository.getEmployees();
    }

    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findEmployeeById(employeeId);
    }

    public List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findEmployeesByPagination(pageIndex, pageSize);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findEmployeesByGender(gender);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeToBeUpdated) {
        return employeeRepository.updateEmployee(employeeId, employeeToBeUpdated);
    }
}
