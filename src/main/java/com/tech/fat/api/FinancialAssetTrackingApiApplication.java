package com.tech.fat.api;

import com.tech.fat.api.controller.UserDetailController;
import com.tech.fat.api.model.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Financial Asset Tracking API", version = "1.0", description = "Financial Asset Information"))
public class FinancialAssetTrackingApiApplication {

    @Autowired
    private UserDetailController userDetailController;

    @PostConstruct
    public void createUsers() {
        User user1 = new User(1, "user1", "password1", "ROLE_USER", true);
        User user2 = new User(2, "user2", "password2", "ROLE_USER", true);
        User user3 = new User(3, "admin", "password", "ROLE_ADMIN", true);
        userDetailController.signUp(user1);
        userDetailController.signUp(user2);
        userDetailController.adminSignUp(user3);
    }
    public static void main(String[] args) {
        SpringApplication.run(FinancialAssetTrackingApiApplication.class, args);
    }

}
