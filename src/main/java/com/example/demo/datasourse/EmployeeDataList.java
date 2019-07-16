package com.example.demo.datasourse;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataList {
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public EmployeeDataList(){
        employees = new ArrayList<>();
        employees.add(new Employee(1,"zhangsan",18,"male",8000));
        employees.add(new Employee(2,"lisi",20,"male",10000));
    }
}
