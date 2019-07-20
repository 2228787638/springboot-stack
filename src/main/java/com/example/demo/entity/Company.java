package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yif
 */
public class Company {
    private long id;
    private String companyName;
    private int employeeNumber;
    private List<Employee> employees;

    public Company(){}
    public Company(long id, String companyName, int employeeNumber, List<Employee> employees) {
        this.id=id;
        this.companyName=companyName;
        this.employeeNumber=employeeNumber;
        this.employees=employees;
    }

    public Company(String companyName, int employeeNumber, List<Employee> employees) {
        this.companyName = companyName;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
