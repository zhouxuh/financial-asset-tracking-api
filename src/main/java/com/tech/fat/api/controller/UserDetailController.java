package com.tech.fat.api.controller;

import com.tech.fat.api.model.User;
import com.tech.fat.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("api/v1/user")
public class UserDetailController {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("signup")
    public ResponseEntity signUp(@RequestBody User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return new ResponseEntity<>(user.getUserName() + " already exists.", HttpStatus.CONFLICT);
        }
        user.setRoles(ROLE_USER);
        user.setActive(true);
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return new ResponseEntity<>(user.getUserName() + " created.", HttpStatus.CREATED);
    }

    @PostMapping("adminsignup")
    public ResponseEntity adminSignUp(@RequestBody User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return new ResponseEntity<>(user.getUserName() + " already exists.", HttpStatus.CONFLICT);
        }
        user.setRoles(ROLE_ADMIN);
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return new ResponseEntity<>(user.getUserName() + " admin created.", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("users")
    public List<User> getUsers() {
       return userRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("testuser")
    public ResponseEntity testUser() {
        return new ResponseEntity<>("Hi user.", HttpStatus.OK);
    }

}
