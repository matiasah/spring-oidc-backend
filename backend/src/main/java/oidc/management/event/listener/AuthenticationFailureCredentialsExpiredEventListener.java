package oidc.management.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureCredentialsExpiredEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureCredentialsExpiredEventListener implements ApplicationListener<AuthenticationFailureCredentialsExpiredEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureCredentialsExpiredEvent event) {
        System.out.println("AuthenticationFailureCredentialsExpiredEventListener");
    }

}
