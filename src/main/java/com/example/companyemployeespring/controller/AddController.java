package com.example.companyemployeespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddController {
    @GetMapping("/addCompany")
    public String addCompany(){
        return "addCompany";
    }

}
