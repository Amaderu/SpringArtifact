package com.amaderu.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private WebClient webClient;

    @GetMapping("/api/hello")
    public String hello(Principal principal) {

        return String.format("Hello %s, check this pages \n<a href=\"%s\">artifacts</a>\n<a href=\"%s\">comments</a>",principal.getName(),
                "http://127.0.0.1:8081/api/artifacts", "http://127.0.0.1:8081/api/comments");
    }



}
