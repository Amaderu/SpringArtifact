package com.amaderu.client.controller;

import com.amaderu.client.entity.User;
import com.amaderu.client.event.RegistrationCompleteEvent;
import com.amaderu.client.model.UserModel;
import com.amaderu.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello unregisted user";
    }
}
