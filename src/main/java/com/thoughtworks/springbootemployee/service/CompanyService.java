package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.OldCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private OldCompanyRepository oldCompanyRepository;

    @Autowired
    private CompanyRepository companyRepository;


    public CompanyService(OldCompanyRepository oldCompanyRepository) {
        this.oldCompanyRepository = oldCompanyRepository;
    }

    public CompanyService() {
    }

    public List<Company> getAllCompanies() {
        return oldCompanyRepository.getAllCompanies();
    }

    public Company getCompanyById(Integer companyId) {
        return oldCompanyRepository.getCompanyById(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return oldCompanyRepository.getCompaniesByPagination(pageIndex, pageSize);
    }

    public void addCompany(Company company) {
        companyRepository.save(company);

    }


    public Company updateCompany(Integer companyId, Company companyToUpdate) {

        return oldCompanyRepository.updateCompany(companyId, companyToUpdate);
    }

    public List<Company> deleteCompany(Integer companyId) {
        return oldCompanyRepository.deleteCompany(companyId);
    }
}
