package oidc.management.service.impl;

import oidc.management.model.AuditEvent;
import oidc.management.repository.AuditEventRepository;
import oidc.management.service.AuditEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Import({
        DefaultAuditEventService.class,
        DefaultAuditEventEncryptionService.class
})
@DataJpaTest
public class DefaultAuditEventServiceTests {

    @Autowired
    private AuditEventService auditEventService;

    @Autowired
    private AuditEventRepository auditEventRepository;

    @Test
    public void testEntityBuilder() {

        // Test entityBuilder
        Assertions.assertNotNull(auditEventService.entityBuilder());

    }

    @Test
    public void testFindAll() {

        // Create test AuditEvent
        AuditEvent auditEvent = auditEventService.entityBuilder()
                .description("test")
                .build();

        // Save auditEvent
        auditEventService.save(auditEvent);

        // Test findAll
        List<AuditEvent> auditEventList = auditEventService.findAll();

        // Validate auditEvents size
        Assertions.assertEquals(1, auditEventList.size());

        // Validate auditEvents
        Assertions.assertEquals("test", auditEventList.get(0).getDescription());

    }

    @Test
    public void testFindAllPage() {

        // Create test AuditEvent
        AuditEvent auditEvent = auditEventService.entityBuilder()
                .description("test")
                .build();

        // Save auditEvent
        auditEventService.save(auditEvent);

        // Test findAll
        Page<AuditEvent> auditEventPage = auditEventService.findAll(Pageable.ofSize(5));

        // Convert to list
        List<AuditEvent> auditEventList = auditEventPage.toList();

        // Validate auditEvents size
        Assertions.assertEquals(1, auditEventPage.getNumberOfElements());

        // Validate auditEvent
        Assertions.assertEquals("test", auditEventList.get(0).getDescription());

    }

    @Test
    public void testFindById() {

        // Create test AuditEvent
        AuditEvent auditEvent = auditEventService.entityBuilder()
                .description("test")
                .build();

        // Save auditEvent
        auditEventService.save(auditEvent);

        // Test findById
        Optional<AuditEvent> optResult = auditEventService.findById(auditEvent.getId());

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals("test", optResult.get().getDescription());

    }

    @Test
    public void testSave() {

        // Create test AuditEvent
        AuditEvent auditEvent = auditEventService.entityBuilder()
                .description("test")
                .build();

        // Save auditEvent
        Assertions.assertNotNull(auditEventService.save(auditEvent));

    }

    @Test
    public void testSaveException() {

        // Spy auditEventRepository
        final AuditEventRepository auditEventRepository = Mockito.mock(AuditEventRepository.class, AdditionalAnswers.delegatesTo(this.auditEventRepository));

        // Spy auditEventService
        final AuditEventService auditEventService = Mockito.spy(this.auditEventService);

        // Set auditEventRepository
        ReflectionTestUtils.setField(auditEventService, "auditEventRepository", auditEventRepository);

        // Create test AuditEvent
        AuditEvent auditEvent = auditEventService.entityBuilder()
                .description("test")
                .build();

        // Mock save
        Mockito.when(auditEventRepository.save(auditEvent)).thenThrow(new DataAccessResourceFailureException("test"));

        // Save auditEvent
        Assertions.assertNotNull(auditEventService.save(auditEvent));

    }

    @Test
    public void testDeleteById() {

        // Create test AuditEvent
        AuditEvent auditEvent = auditEventService.entityBuilder()
                .description("test")
                .build();

        // Save auditEvent
        auditEventService.save(auditEvent);

        // Test deleteById
        auditEventService.deleteById(auditEvent.getId());

    }

    @Test
    public void testDeleteByIdException() {

        // Spy auditEventRepository
        final AuditEventRepository auditEventRepository = Mockito.mock(AuditEventRepository.class, AdditionalAnswers.delegatesTo(this.auditEventRepository));

        // Spy auditEventService
        final AuditEventService auditEventService = Mockito.spy(this.auditEventService);

        // Set auditEventRepository
        ReflectionTestUtils.setField(auditEventService, "auditEventRepository", auditEventRepository);

        // Mock deleteById
        Mockito.doThrow(new DataAccessResourceFailureException("test")).when(auditEventRepository).deleteById("test");

        // Test deleteById
        auditEventService.deleteById("test");

    }

}
