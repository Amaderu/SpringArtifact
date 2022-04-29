package com.amaderu.client.event.listener;

import com.amaderu.client.entity.User;
import com.amaderu.client.event.RegistrationCompleteEvent;
import com.amaderu.client.service.UserService;
import com.amaderu.client.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create verification token for the User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token, user);

        //send verify email
        String url =
                event.getApplicationUrl()
                        + "/verifyRegistration?token="
                        + token;

        //TODO Chang to web page
        log.info("Click link to verify account {}", url);
    }
}
