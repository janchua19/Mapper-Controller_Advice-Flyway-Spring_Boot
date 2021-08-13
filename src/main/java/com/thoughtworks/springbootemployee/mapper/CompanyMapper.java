package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyResponse toResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);

        companyResponse.setEmployeesNumber(company.getEmployees().size());

        return companyResponse;
    }
}
