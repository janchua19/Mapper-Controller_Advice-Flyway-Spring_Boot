package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        employees.forEach(employee ->employeeResponses.add(employeeMapper.toResponse(employee)));
        return employeeResponses;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse findById(@PathVariable Integer employeeId) {
        final Employee employee = employeeService.findEmployeeById(employeeId);
        return employeeMapper.toResponse(employee);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<EmployeeResponse> findEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        List<Employee> employees = employeeService.findEmployeesByPagination(pageIndex, pageSize);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        employees.forEach(employee -> employeeResponses.add(employeeMapper.toResponse(employee)));
        return employeeResponses;
    }

    @GetMapping(params = "gender")
    public List<EmployeeResponse> findEmployeesByGender(@RequestParam(required = true) String gender) {
        List<Employee> employees = employeeService.findEmployeesByGender(gender);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        employees.forEach(employee -> employeeResponses.add(employeeMapper.toResponse(employee)));
        return employeeResponses;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        final Employee employee = employeeService.addEmployee(employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeResponse updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.updateEmployee(employeeId, employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);

    }
}