package com.thoughtworks.springbootemployee.repository;


import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByGender(String gender);

//    @Query(value = "SELECT * FROM EMPLOYEE WHERE  ", nativeQuery = true)
//    List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize);
//        return employees
//                .stream()
//                .skip((long) (pageIndex - 1) * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());


    //  Employee findById(int id);
}
