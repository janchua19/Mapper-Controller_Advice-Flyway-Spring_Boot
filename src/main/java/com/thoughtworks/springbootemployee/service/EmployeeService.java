package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.OldEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private OldEmployeeRepository oldEmployeeRepository;


    @Autowired
    private EmployeeRepository employeeRepository;

//    public EmployeeService(OldEmployeeRepository oldEmployeeRepository) {
//        this.oldEmployeeRepository = oldEmployeeRepository;
//    }


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {

//        return oldEmployeeRepository.getEmployees();
        return employeeRepository.findAll();

    }

    public Employee findEmployeeById(Integer employeeId) {
        return oldEmployeeRepository.findEmployeeById(employeeId);
    }

    public List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return oldEmployeeRepository.findEmployeesByPagination(pageIndex, pageSize);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return oldEmployeeRepository.findEmployeesByGender(gender);
    }

    public void addEmployee(Employee employee) {

//        oldEmployeeRepository.addEmployee(employee);
        employeeRepository.save(employee);

    }

    public Employee updateEmployee(Integer employeeId, Employee employeeToBeUpdated) {
        return oldEmployeeRepository.updateEmployee(employeeId, employeeToBeUpdated);
    }

    public void deleteEmployee(Integer employeeId) {
        oldEmployeeRepository.deleteEmployee(employeeId);
    }
}
