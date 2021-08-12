package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.OldEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex - 1,pageSize)).getContent();
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeToBeUpdated) {
        return oldEmployeeRepository.updateEmployee(employeeId, employeeToBeUpdated);

    }

    public void deleteEmployee(Integer employeeId) {
        Employee employeeToDelete = findEmployeeById(employeeId);
        employeeRepository.delete(employeeToDelete);
    }
}
