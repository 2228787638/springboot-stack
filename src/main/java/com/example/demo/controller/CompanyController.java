package com.example.demo.controller;

import com.example.demo.datasourse.CompanyDataList;
import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yif
 */
@RestController
public class CompanyController {
    @Autowired
    private CompanyDataList companyDataList;
    @GetMapping("/companies")
    @ResponseBody
    public List<Company> getCompanies(){
        return companyDataList.getCompanyList();
    }
    @GetMapping("/companies/{companyId}")
    @ResponseBody
    public Company getCompaniesById(@PathVariable String companyId){
        return companyDataList.getCompanyList().stream()
                .filter(item->item.getId()==Long.parseLong(companyId))
                .findFirst().get();
    }

    @GetMapping("/companies/{companyId}/employees")
    @ResponseBody
    public List<Employee> getEmployeesByCompanyId(@PathVariable String companyId){
        return companyDataList.getCompanyList().stream()
                .filter(item->item.getId()==Long.parseLong(companyId))
                .findFirst().get().getEmployees();
    }
    @GetMapping(value="/companies",params ={"page","pageSize"})
    @ResponseBody
    public List<Company> getCompaniesByPageAndPageSize(@RequestParam int page,@RequestParam int pageSize){
        return companyDataList.getCompanyList().stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
    @PostMapping("/companies")
    @ResponseBody
    public Company addCompany(@RequestBody Company company){
        company.setId(3);
        companyDataList.getCompanyList().add(company);
        return company;
    }
    @PutMapping("/companies/{companyId}")
    @ResponseBody
    public Company updateCompany(@PathVariable String companyId,@RequestBody Company company){
        Company updatedCompany=companyDataList.getCompanyList().stream().filter(item->item.getId()==Long.parseLong(companyId)).findFirst().get();
        updatedCompany.setCompanyName(company.getCompanyName());
        updatedCompany.setEmployeeNumber(company.getEmployeeNumber());
        updatedCompany.setEmployees(company.getEmployees());
        return updatedCompany;
    }
    @DeleteMapping("/companies/{companyId}")
    @ResponseBody
    public Company deleteCompany(@PathVariable String companyId){
        Company company=companyDataList.getCompanyList().stream().filter(item->item.getId()==Long.parseLong(companyId)).findFirst().get();
        companyDataList.getCompanyList().remove(company);
        return company;
    }
}
