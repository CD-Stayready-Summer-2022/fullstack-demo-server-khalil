package com.example.fullstackdemo;

import com.example.fullstackdemo.domain.employee.models.Employee;
import com.example.fullstackdemo.domain.employee.services.EmployeeService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    private Employee preSaveMockEmployee;
    private Employee postSaveMockEmployee;

    @BeforeEach
    void setUp() {
        preSaveMockEmployee = new Employee("Luis", "Adorno", "luis@fakemail.com");
        postSaveMockEmployee = new Employee("Luis", "Adorno", "luis@fakemail.com");
        postSaveMockEmployee.setId(4l);
    }

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    @DisplayName("getAllEmployees - success")
    public void EmployeeControllerTest01() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createEmployeeTest01() throws Exception {
        BDDMockito.doReturn(postSaveMockEmployee).when(employeeService).create(preSaveMockEmployee);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringify.asJsonString(preSaveMockEmployee)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(4)));
    }


}