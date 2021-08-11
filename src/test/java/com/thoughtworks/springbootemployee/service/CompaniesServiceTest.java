package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CompaniesServiceTest {
    @InjectMocks
    CompanyService companyService;
    @Mock
    CompanyRepository companyRepository;


    @Test
    public void should_return_all_companies_when_getAllCompanies_given_none() {
        //given
        Employee employee1 = new Employee(1, "Ian", 44, "Male", 20000);
        Employee employee2 = new Employee(2, "Rhea", 44, "Female", 30000);
        List<Company> companies = new ArrayList<>();

        companies.add(new Company("ABC", 1, Collections.singletonList(employee1)));
        companies.add(new Company("DEF", 1, Collections.singletonList(employee2)));
        given(companyRepository.getAllCompanies()).willReturn(companies);
        //when
        List<Company> actualCompanies = companyService.getAllCompanies();

        //then
        assertIterableEquals(companies, actualCompanies);


    }
}
