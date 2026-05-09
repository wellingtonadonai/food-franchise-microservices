package com.wellington.auth_service.controller;

import com.wellington.auth_service.entity.User;
import com.wellington.auth_service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public String login(@RequestBody User user) {
        if ("wellington".equals(user.getUsername()) && "123".equals(user.getPassword())) {
            return jwtService.generateToken(user.getUsername());
        }
        throw new RuntimeException("Usuário ou senha inválidos!");
    }
}
