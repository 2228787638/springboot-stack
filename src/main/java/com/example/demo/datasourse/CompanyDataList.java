package com.example.demo.datasourse;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yif
 */
public class CompanyDataList {
    private List<Company> companyList;

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public CompanyDataList() {
        companyList = new ArrayList<>();
        List<Employee> employees=new ArrayList<>();
        List<Employee> employees1=new ArrayList<>();
        employees.add(new Employee(1,"zhangsan",18,"male",8000));
        employees1.add(new Employee(1,"lisi",20,"male",10000));
        companyList.add(new Company(1,"tencent",1,employees));
        companyList.add(new Company(2,"alibaba",1,employees1));
    }
}
