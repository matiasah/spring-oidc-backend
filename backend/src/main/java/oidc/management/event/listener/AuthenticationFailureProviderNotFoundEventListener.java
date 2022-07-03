package oidc.management.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureProviderNotFoundEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureProviderNotFoundEventListener implements ApplicationListener<AuthenticationFailureProviderNotFoundEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureProviderNotFoundEvent event) {
        System.out.println("AuthenticationFailureProviderNotFoundEventListener");
    }

}
