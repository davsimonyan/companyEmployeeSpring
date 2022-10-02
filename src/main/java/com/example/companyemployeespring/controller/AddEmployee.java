package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AddEmployee {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/addEmployee")
    public String addEmployee(ModelMap modelMap){
        List<Company> all = companyRepository.findAll();
        modelMap.addAttribute("companies", all);
        return "addEmployee";
    }

}
