package com.example.OauthResourceServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/api/users")
    public String[] getUser(){
        return new String[]{
                "test1",
                "test2",
                "test3"
        };
    }
}
