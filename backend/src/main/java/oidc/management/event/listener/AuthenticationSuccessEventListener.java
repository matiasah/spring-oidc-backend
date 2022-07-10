package oidc.management.event.listener;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private AuditEventService auditEventService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        // Create AuditEvent
        AuditEvent auditEvent = this.auditEventService.entityBuilder()
                .description(String.format("Authentication success for \"%s\"", event.getAuthentication().getName()))
                .build();

        // Save the audit event
        auditEventService.save(auditEvent);

        // Log the audit event
        log.info(auditEvent);

    }

}
