package com.amaderu.client.event;

import com.amaderu.client.entity.User;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompletEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;
    public RegistrationCompletEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
