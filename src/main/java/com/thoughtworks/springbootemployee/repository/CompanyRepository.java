package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        companies.add(new Company(1, "ABC", 5, abcEmployees));
        companies.add(new Company(2, "DEF", 2, defEmployees));

    }

    public List<Company> getAllCompanies() {
        return companies;

    }

    public Company getCompanyById(Integer companyId) {
        return companies.stream().filter(company -> company.getCompanyId().equals(companyId)).findFirst().orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        Company filteredCompany = getCompanyById(companyId);
        return filteredCompany.getEmployees();

    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companies
                .stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
