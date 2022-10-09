package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.User;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.UserRepository;
import com.example.companyemployeespring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


//    @GetMapping("/login")
//    public String userHome() {
//        return "login";
//    }

    @GetMapping("/login")
    public String users(ModelMap modelMap) {
        List<User> all = userService.findAllUsers();
        modelMap.addAttribute("users", all);
        return "login";
    }

    @PostMapping("/login/index")
    public String addUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent() &&  byEmail.get().getPassword().equals(password)) {
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(ModelMap modelMap) {
        return "register";
    }

    @PostMapping("/register/add")
    public String addUser(@ModelAttribute User user, ModelMap modelMap) throws IOException {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            modelMap.addAttribute("errorMessageEmail", "Email already in use");
            return "addUser";
        }
        userService.saveUser(user);
        return "redirect:/";
    }



//    @GetMapping(value = "/users/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody
//    byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
//        return userService.getUserImage(fileName);
//    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/";
    }

}