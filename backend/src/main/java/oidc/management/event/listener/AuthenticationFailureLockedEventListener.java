package oidc.management.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureLockedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureLockedEventListener implements ApplicationListener<AuthenticationFailureLockedEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureLockedEvent event) {
        System.out.println("AuthenticationFailureLockedEventListener.onApplicationEvent");
    }

}
