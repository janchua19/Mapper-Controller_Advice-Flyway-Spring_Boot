package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {

   private List<Company> companies = new ArrayList<>();
    private EmployeeRepository employeeRepository = new EmployeeRepository();

    public CompanyRepository() {
        List<Employee> employees = employeeRepository.getEmployees();
        List<Employee> abcEmployees = new ArrayList<>();
        abcEmployees.add(employees.get(0));
        abcEmployees.add(employees.get(1));
        abcEmployees.add(employees.get(2));
        abcEmployees.add(employees.get(3));
        abcEmployees.add(employees.get(4));

        List<Employee> defEmployees = new ArrayList<>();
        defEmployees.add(employees.get(5));
        defEmployees.add(employees.get(6));

        companies.add(new Company("ABC", 5, abcEmployees));
        companies.add(new Company("DEF", 2, defEmployees));

    }

    public List<Company> getAllCompanies() {
        return companies;

    }
}
