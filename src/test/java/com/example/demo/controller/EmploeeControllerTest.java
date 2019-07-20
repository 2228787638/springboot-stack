package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmploeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getEmploees() throws Exception {
        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("zhangsan"))
                .andExpect(jsonPath("$[1].name").value("lisi"));
    }

    @Test
    public void getEmploeesById() throws Exception {
    }

    @Test
    public void getEmployeesByPageAndPageSize() throws Exception {
    }

    @Test
    public void getEmployeesByGender() throws Exception {
    }

    @Test
    public void addEmployees() throws Exception {
    }

    @Test
    public void updateEmployees() throws Exception {
    }

    @Test
    public void deleteEmployees() throws Exception {
    }

}