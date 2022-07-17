package oidc.management.controller;

import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = {
        AuditEventController.class
})
public class AuditEventControllerTests {

    @MockBean
    private AuditEventService auditEventService;

    @Autowired
    private AuditEventController auditEventController;

    private List<AuditEvent> all = new ArrayList<>();

    @Mock
    private Pageable pageable;

    @Mock
    private Page page;

    @Mock
    private AuditEvent auditEvent;

    @Test
    public void testIndex() {
        // Mock findAll() method
        Mockito.when(auditEventService.findAll()).thenReturn(all);

        // Test
        Assertions.assertEquals(all, auditEventController.index());
    }

    @Test
    public void testPage() {
        // Mock findAll
        Mockito.when(auditEventService.findAll(Mockito.eq(pageable))).thenReturn(page);

        // Test
        Assertions.assertEquals(page, auditEventController.page(pageable));
    }

    @Test
    public void testGet() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<AuditEvent> optional = Optional.of(auditEvent);

        // Mock findById
        Mockito.when(auditEventService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<AuditEvent> responseEntity = auditEventController.get(id);

        // Validate body
        Assertions.assertEquals(auditEvent, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetNoContent() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<AuditEvent> optional = Optional.empty();

        // Mock findById
        Mockito.when(auditEventService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<AuditEvent> responseEntity = auditEventController.get(id);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

}
