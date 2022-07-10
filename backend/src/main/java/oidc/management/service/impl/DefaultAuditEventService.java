package oidc.management.service.impl;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.AuditEvent;
import oidc.management.repository.AuditEventRepository;
import oidc.management.service.AuditEventEncryptionService;
import oidc.management.service.AuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link AuditEventService}.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
@Log4j2
public class DefaultAuditEventService implements AuditEventService {

    @Autowired
    private AuditEventRepository auditEventRepository;

    @Autowired
    private AuditEventEncryptionService auditEventEncryptionService;

    @Override
    public AuditEvent.AuditEventBuilder entityBuilder() {
        return auditEventRepository.entityBuilder();
    }

    @Override
    public List<AuditEvent> findAll() {
        // Find all auditEvents
        return (List<AuditEvent>) auditEventRepository.findAll()
                .stream()
                .map(
                        // Decrypt auditEvent
                        auditEvent -> auditEventEncryptionService.decrypt((AuditEvent) auditEvent)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuditEvent> findAll(Pageable pageable) {
        // Find a page of auditEvents
        return auditEventRepository.findAll(pageable)
                .map(
                        // Decrypt auditEvent
                        auditEvent -> auditEventEncryptionService.decrypt((AuditEvent) auditEvent)
                );
    }

    @Override
    public Optional<AuditEvent> findById(String id) {
        // Find auditEvent by its id
        return auditEventRepository.findById(id)
                .map(
                        // Decrypt auditEvent
                        auditEvent -> auditEventEncryptionService.decrypt((AuditEvent) auditEvent)
                );
    }

    @Override
    public AuditEvent save(AuditEvent auditEvent) {
        // Encrypt auditEvent
        AuditEvent encryptedAuditEvent = auditEventEncryptionService.encrypt(auditEvent);

        try {
            // Save auditEvent
            this.auditEventRepository.save(encryptedAuditEvent);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems persisting auditEvent", e);
        }

        // Set id
        auditEvent.setId(encryptedAuditEvent.getId());

        // Return auditEvent
        return auditEvent;
    }

    @Override
    public void deleteById(String id) {
        try {
            // Delete auditEvent by its id
            auditEventRepository.deleteById(id);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems deleting auditEvent", e);
        }
    }

}
