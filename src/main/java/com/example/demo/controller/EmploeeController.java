package com.example.demo.controller;

import com.example.demo.datasourse.CompanyDataList;
import com.example.demo.datasourse.EmployeeDataList;
import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class EmploeeController {
    private EmployeeDataList employeeDataList = new EmployeeDataList();
    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> getEmploees(){
        return employeeDataList.getEmployees();
    }
    @GetMapping("/employees/{employeeId}")
    @ResponseBody
    public Employee getEmploeesById(@PathVariable String employeeId){
        return employeeDataList.getEmployees().stream()
                .filter(item->item.getId()==Long.parseLong(employeeId))
                .findFirst().get();
    }
    @GetMapping(value="/employees",params ={"page","pageSize"})
    @ResponseBody
    public List<Employee> getEmployeesByPageAndPageSize(@RequestParam int page, @RequestParam int pageSize){
        return employeeDataList.getEmployees().stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

    }
    @GetMapping(value="/employees",params ="gender")
    @ResponseBody
    public List<Employee> getEmployeesByGender(@RequestParam String gender){
        return employeeDataList.getEmployees().stream()
                .filter(item->item.getGender().equals(gender))
                .collect(Collectors.toList());
    }
    @PostMapping("/employees")
    @ResponseBody
    public Employee addEmployees(@RequestBody Employee employee){
        employee.setId(3);
        employeeDataList.getEmployees().add(employee);
        return employee;
    }
    @PutMapping("/employees/{employeeId}")
    @ResponseBody
    public Employee updateEmployees(@PathVariable String employeeId,@RequestBody Employee employee){
        Employee updatedEmployee=employeeDataList.getEmployees().stream().filter(item->item.getId()==Long.parseLong(employeeId)).findFirst().get();
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setAge(employee.getAge());
        updatedEmployee.setGender(employee.getGender());
        return updatedEmployee;
    }
    @DeleteMapping("/employees/{employeeId}")
    @ResponseBody
    public Employee deleteEmployees(@PathVariable String employeeId){
        Employee employee=employeeDataList.getEmployees().stream().filter(item->item.getId()==Long.parseLong(employeeId)).findFirst().get();
        employeeDataList.getEmployees().remove(employee);
        return employee;
    }
}
