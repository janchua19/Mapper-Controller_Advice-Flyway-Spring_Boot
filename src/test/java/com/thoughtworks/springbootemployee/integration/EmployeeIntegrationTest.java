package com.thoughtworks.springbootemployee.integration;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

//    @AfterEach
//    public void after(){
//        employeeRepository.deleteAll();
//    }

    @Test
    void should_return_all_employees_when_call_get_all_employees_api() throws Exception {
        //given
        Employee employee = new Employee(1, "Ian", 12, "Male", 2000);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Ian"))
                .andExpect(jsonPath("$[0].age").value(12))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(2000));
    }

    @Test
    void should_create_employee_when_call_add_Employee_api() throws Exception {
        //given
        String employee = "{\n" +
                "    \"id\" : 1,\n" +
                "    \"name\" : \"Jan\",\n" +
                "    \"age\" : 21,\n" +
                "    \"gender\" : \"Male\",\n" +
                "    \"salary\" : 30000\n" +
                "}";

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jan"))
                .andExpect(jsonPath("$.age").value("21"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("30000"));
    }

    @Test
    public void should_update_employee_when_call_update_employee_api() throws Exception {
        //given
        final Employee employee = new Employee(300, "Ian", 12, "Male", 2000);
        final Employee savedEmployee = employeeRepository.save(employee);
        String employeeWithNewInfo = "{\n" +
                "    \"id\" : 300,\n" +
                "    \"name\" : \"Ian\",\n" +
                "    \"age\" : 30,\n" +
                "    \"gender\" : \"Male\",\n" +
                "    \"salary\" : 30000\n" +
                "}";

        //when
        //then
        int id = savedEmployee.getId();

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeWithNewInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ian"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(30000));
    }

    @Test
    void should_return_all_employees_By_Gender_when_call_get_All_Employees_By_Gender_employees_api() throws Exception
    {
        //given
        Employee employee1 = new Employee(1, "Ian", 60, "Male", 40000);
        Employee savedEmployee = employeeRepository.save(employee1);
        Employee employee2 = new Employee(2, "Leo", 45, "Male", 30000);
        employeeRepository.save(employee2);
        Employee employee3 = new Employee(3, "Rhea", 30, "Male", 20000);
        employeeRepository.save(employee3);

        //when
        //then

        String gender = savedEmployee.getGender();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/?gender={gender}",gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ian"))
                .andExpect(jsonPath("$[0].age").value(60))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(40000))
                .andExpect(jsonPath("$[1].name").value("Leo"))
                .andExpect(jsonPath("$[1].age").value(45))
                .andExpect(jsonPath("$[1].gender").value("Male"))
                .andExpect(jsonPath("$[1].salary").value(30000));
    }
}
