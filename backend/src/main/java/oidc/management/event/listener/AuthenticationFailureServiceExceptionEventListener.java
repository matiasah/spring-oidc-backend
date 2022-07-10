package oidc.management.event.listener;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AuthenticationFailureServiceExceptionEventListener implements ApplicationListener<AuthenticationFailureServiceExceptionEvent> {

    @Autowired
    private AuditEventService auditEventService;

    @Override
    public void onApplicationEvent(AuthenticationFailureServiceExceptionEvent event) {

        // Create AuditEvent
        AuditEvent auditEvent = this.auditEventService.entityBuilder()
                .description(String.format("Authentication failure, exception ocurred \"%s\" for \"%s\"", event.getException().getMessage(), event.getAuthentication().getPrincipal()))
                .build();

        // Save the audit event
        auditEventService.save(auditEvent);

        // Log the audit event
        log.warn(auditEvent);

    }

}
