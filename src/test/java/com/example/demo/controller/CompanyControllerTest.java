package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void should_return_all_companies_by_getCompanies() throws Exception {
        mockMvc.perform(get("/companies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"companyName\":\"tencent\"" +
                        ",\"employeeNumber\":1,\"employees\":[{\"id\":1,\"name\":\"zhangsan\"" +
                        ",\"age\":18,\"gender\":\"male\",\"salary\":8000.0}]},{\"id\":2,\"companyName\":\"alibaba\"" +
                        ",\"employeeNumber\":1,\"employees\":[{\"id\":1,\"name\":\"lisi\",\"age\":20" +
                        ",\"gender\":\"male\",\"salary\":10000.0}]}]"));
    }

    @Test
    public void getCompaniesById() throws Exception {
        mockMvc.perform(get("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"companyName\":\"tencent\",\"employeeNumber\":1," +
                        "\"employees\":[{\"id\":1,\"name\":\"zhangsan\",\"age\":18,\"gender\":\"male\"," +
                        "\"salary\":8000.0}]}"));
    }

    @Test
    public void getEmployeesByCompanyId() throws Exception {
        mockMvc.perform(get("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"companyName\":\"tencent\",\"employeeNumber\":1," +
                        "\"employees\":[{\"id\":1,\"name\":\"zhangsan\",\"age\":18,\"gender\":\"male\"," +
                        "\"salary\":8000.0}]}"));
    }

    @Test
    public void getCompaniesByPageAndPageSize() throws Exception {
        mockMvc.perform(get("/companies?page=1&pageSize=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"companyName\":\"tencent\",\"employeeNumber\":1," +
                        "\"employees\":[{\"id\":1,\"name\":\"zhangsan\",\"age\":18,\"gender\":\"male\",\"salary\":8000.0}]}]"));
    }

    @Test
    public void addCompany() throws Exception {
        List<Employee> employees=new ArrayList<>();

        employees.add(new Employee(3,"guge",33,"female",18000));

        mockMvc.perform(post("/companies").content("[{\"companyName\":\"baidu\",\"employeeNumber\":1,\"employees\":[{\"id\":3,\"name\":\"guge\",\"age\":33,\"gender\":\"female\",\"salary\":18000.0}]}]"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":3,\"companyName\":\"baidu\",\"employeeNumber\":1,\"employees\":[{\"id\":3,\"name\":\"guge\",\"age\":33,\"gender\":\"female\",\"salary\":18000.0}]}]"));
    }

    @Test
    public void updateCompany() throws Exception {
    }

    @Test
    public void deleteCompany() throws Exception {
    }

}