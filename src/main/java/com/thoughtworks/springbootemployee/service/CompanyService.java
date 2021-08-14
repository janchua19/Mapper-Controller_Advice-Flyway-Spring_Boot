package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company not found."));
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company not found."));
        return company.getEmployees();
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageIndex -1, pageSize)).getContent();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }


    public Company updateCompany(Integer companyId, Company companyToUpdate) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        return companyRepository.save(Objects.requireNonNull(updateCompanyInformation(company,
                companyToUpdate)));
    }

    private Company updateCompanyInformation(Company company, Company companyToUpdate) {
        if (companyToUpdate.getCompanyName() != null) {
            company.setCompanyName(companyToUpdate.getCompanyName());
        }
        if (companyToUpdate.getEmployees() != null) {
            company.setEmployees(companyToUpdate.getEmployees());
        }
        return company;
    }

    public void deleteCompany(Integer companyId) {
        companyRepository.deleteById(companyId);
    }
}
