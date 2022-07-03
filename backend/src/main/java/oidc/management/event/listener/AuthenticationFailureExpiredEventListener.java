package oidc.management.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureExpiredEventListener implements ApplicationListener<AuthenticationFailureExpiredEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureExpiredEvent event) {

    }

}
