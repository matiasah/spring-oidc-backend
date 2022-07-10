package oidc.management.service;

import oidc.management.model.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing audit events
 *
 * @author Matías Hermosilla
 * @since 10-07-2022
 */
public interface AuditEventService {

    /**
     * Creates an object that is used to build instances of {@link AuditEvent}.
     *
     * @return An instance of {@link oidc.management.model.AuditEvent.AuditEventBuilder}.
     */
    public AuditEvent.AuditEventBuilder entityBuilder();

    /**
     * Finds all {@link AuditEvent}s.
     *
     * @return List of {@link AuditEvent}s.
     */
    public List<AuditEvent> findAll();

    /**
     * Finds a page of {@link AuditEvent}s.
     *
     * @param pageable Pageable object.
     * @return Page of {@link AuditEvent}.
     */
    public Page<AuditEvent> findAll(Pageable pageable);

    /**
     * Finds an {@link AuditEvent} by its id.
     *
     * @param id The id of the {@link AuditEvent}.
     * @return The {@link AuditEvent}.
     */
    public Optional<AuditEvent> findById(String id);

    /**
     * Saves an {@link AuditEvent}.
     *
     * @param auditEvent The {@link AuditEvent} to save.
     * @return The saved {@link AuditEvent}.
     */
    public AuditEvent save(AuditEvent auditEvent);

    /**
     * Deletes an {@link AuditEvent} by its id.
     *
     * @param id The id of the {@link AuditEvent}.
     */
    public void deleteById(String id);

}
