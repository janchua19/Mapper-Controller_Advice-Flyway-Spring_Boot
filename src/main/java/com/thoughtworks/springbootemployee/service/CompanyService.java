package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyService() {
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        return companyRepository.getEmployeesByCompanyId(companyId);
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.getCompaniesByPagination(pageIndex, pageSize);
    }

    public void addCompany(Company company) {
        companyRepository.addCompany(company);

    }


    public Company updateCompany(Integer companyId, Company companyToUpdate) {

    return  companyRepository.updateCompany(companyId,companyToUpdate);
    }
}
