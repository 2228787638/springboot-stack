package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                .andExpect(jsonPath("$[0].companyName").value("tencent"))
                .andExpect(jsonPath("$[1].companyName").value("alibaba"));
    }

    @Test
    public void getCompaniesById() throws Exception {
        mockMvc.perform(get("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("tencent"));
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
                .andExpect(jsonPath("$[0].companyName").value("tencent"))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void addCompany() throws Exception {

        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"companyName\":\"baidu\",\n" +
                        "        \"employeeNumber\":1,\n" +
                        "        \"employees\":null\n" +
                        "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("baidu"));
    }

    @Test
    public void updateCompany() throws Exception {

        mockMvc.perform(put("/companies/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"companyName\":\"tencent\",\n" +
                        "        \"employeeNumber\":2,\n" +
                        "        \"employees\":null\n" +
                        "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeNumber").value(2));
    }

    @Test
    public void deleteCompany() throws Exception {
    }

}