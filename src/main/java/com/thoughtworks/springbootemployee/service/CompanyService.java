package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.OldCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private OldCompanyRepository oldCompanyRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageIndex -1, pageSize)).getContent();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);

    }


    public Company updateCompany(Integer companyId, Company companyToUpdate) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return companyRepository.save(company);
    }

    public List<Company> deleteCompany(Integer companyId) {
        return oldCompanyRepository.deleteCompany(companyId);
    }
}
