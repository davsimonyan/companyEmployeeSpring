package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.User;
import com.example.companyemployeespring.repository.UserRepository;
import com.example.companyemployeespring.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DateUtil dateUtil;

    private final MailService mailService;

    //    @Value("${task.management.images.folder}")
    private String folderPath;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        mailService.sendEmail(user.getEmail(), "Welocome",
                "Hi" + user.getName() + "\n" +
                        "You have successfully registered!!!");
    }

    public byte[] getUserImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}