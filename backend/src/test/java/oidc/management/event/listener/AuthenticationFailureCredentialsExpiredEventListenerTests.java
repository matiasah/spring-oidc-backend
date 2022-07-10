package oidc.management.event.listener;

import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventService;
import oidc.management.test.TestAuditEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.event.AuthenticationFailureCredentialsExpiredEvent;
import org.springframework.security.core.Authentication;

import java.util.UUID;

@SpringBootTest(classes = {
        AuthenticationFailureCredentialsExpiredEventListener.class,
        ApplicationEventPublisher.class
})
public class AuthenticationFailureCredentialsExpiredEventListenerTests {

    @Autowired
    private AuthenticationFailureCredentialsExpiredEventListener listener;

    @Autowired
    private ApplicationEventPublisher publisher;

    @MockBean
    private AuditEventService auditEventService;

    @Mock
    private AuthenticationFailureCredentialsExpiredEvent event;

    @Mock
    private Authentication authentication;

    @Test
    public void testOnApplicationEvent() {

        // Mock getAuthentication
        Mockito.when(event.getAuthentication()).thenReturn(authentication);

        // Mock getPrincipal
        Mockito.when(authentication.getPrincipal()).thenReturn(UUID.randomUUID().toString());

        // Mock entityBuilder
        Mockito.when(auditEventService.entityBuilder()).thenReturn(TestAuditEvent.builder());

        // Dispatch event
        publisher.publishEvent(event);

        // Verify save
        Mockito.verify(auditEventService).save(Mockito.any(AuditEvent.class));

    }

}
