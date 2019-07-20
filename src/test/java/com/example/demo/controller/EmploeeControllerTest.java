package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        mockMvc.perform(get("/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zhangsan"));

    }

    @Test
    public void getEmployeesByPageAndPageSize() throws Exception {
        mockMvc.perform(get("/employees?page=1&pageSize=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("zhangsan"))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void getEmployeesByGender() throws Exception {
        mockMvc.perform(get("/employees?gender=male"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("zhangsan"))
                .andExpect(jsonPath("$[1].name").value("lisi"));
    }

    @Test
    public void addEmployees() throws Exception {
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"name\":\"mayun\",\n" +
                        "        \"age\":52,\n" +
                        "        \"gender\":\"female\",\n" +
                        "        \"salary\":9000\n" +
                        "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("mayun"));
    }

    @Test
    public void updateEmployees() throws Exception {
        mockMvc.perform(put("/employees/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"name\":\"zhangsan\",\n" +
                        "        \"age\":56,\n" +
                        "        \"gender\":\"female\",\n" +
                        "        \"salary\":9000\n" +
                        "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(56));
    }

    @Test
    public void deleteEmployees() throws Exception {
        mockMvc.perform(delete("/employees/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("mayun"));
    }

}