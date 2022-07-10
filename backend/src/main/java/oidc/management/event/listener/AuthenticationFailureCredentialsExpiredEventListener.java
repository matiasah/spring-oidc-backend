package oidc.management.event.listener;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureCredentialsExpiredEvent;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AuthenticationFailureCredentialsExpiredEventListener implements ApplicationListener<AuthenticationFailureCredentialsExpiredEvent> {

    @Autowired
    private AuditEventService auditEventService;

    @Override
    public void onApplicationEvent(AuthenticationFailureCredentialsExpiredEvent event) {

        // Create AuditEvent
        AuditEvent auditEvent = this.auditEventService.entityBuilder()
                .description(String.format("Authentication failure, credentials expired with authentication \"%s\"", event.getAuthentication().getPrincipal()))
                .build();

        // Save the audit event
        auditEventService.save(auditEvent);

        // Log the audit event
        log.warn(auditEvent);

    }

}
