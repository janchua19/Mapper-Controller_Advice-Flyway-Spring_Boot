package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
}
