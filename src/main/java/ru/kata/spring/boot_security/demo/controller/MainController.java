package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @GetMapping("/admin")
    public String getAllUsers() {
        return "index";
    }

    @GetMapping("/user")
    public String getUserById() {
        return "user";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}