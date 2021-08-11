package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {

    @InjectMocks
    private EmployeeService employeeService;


    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_none() {

        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Ian", 44, "Female", 20000));
        employees.add(new Employee(2, "Adomar", 50, "Male", 1000));
        given(employeeRepository.getEmployees())
                .willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then

        assertEquals(employees.size(), actualEmployees.size());
        assertIterableEquals(employees, actualEmployees);

    }

    @Test
    public void should_return_all_employees_when_findEmployeeById_given_employeeId() {

        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Ian", 44, "Female", 20000));
        employees.add(new Employee(2, "Adomar", 50, "Male", 1000));
        given(employeeRepository.findEmployeeById(1))
                .willReturn(employees.get(0));

        //when
        Employee actualEmployee = employeeService.findEmployeeById(1);
        //then

        assertEquals(employees.get(0), actualEmployee);

    }


}
