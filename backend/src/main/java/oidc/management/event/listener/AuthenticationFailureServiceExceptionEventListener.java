package oidc.management.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureServiceExceptionEventListener implements ApplicationListener<AuthenticationFailureServiceExceptionEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureServiceExceptionEvent event) {
        System.out.println("AuthenticationFailureServiceExceptionEventListener: " + event.getException().getMessage());
    }

}
