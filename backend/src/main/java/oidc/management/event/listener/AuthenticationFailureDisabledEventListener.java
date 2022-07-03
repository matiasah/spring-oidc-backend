package oidc.management.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureDisabledEventListener implements ApplicationListener<AuthenticationFailureDisabledEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureDisabledEvent event) {
        System.out.println("AuthenticationFailureDisabledEventListener.onApplicationEvent()");
    }

}
