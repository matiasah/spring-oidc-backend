package oidc.management.controller;

import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/audit-events")
public class AuditEventController {

    @Autowired
    private AuditEventService auditEventService;

    /**
     * Get all audit events.
     *
     * @return List of audit events.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_audit_event')")
    @GetMapping
    public List<AuditEvent> index() {
        // Return list of audit events
        return auditEventService.findAll();
    }

    /**
     * Get all audit events.
     *
     * @return Page of audit events.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_audit_event')")
    @GetMapping("page")
    public Page<AuditEvent> page(Pageable pageable) {
        // Return page of audit events
        return this.auditEventService.findAll(pageable);
    }

    /**
     * Get an audit event by id.
     *
     * @param id Audit event id.
     * @return Audit event.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_audit_event')")
    @GetMapping("{id}")
    public ResponseEntity<AuditEvent> get(@PathVariable("id") String id) {
        // Get the optional holder
        Optional<AuditEvent> optObject = this.auditEventService.findById(id);

        // Verify if the holder contains a value
        if (optObject.isPresent()) {
            // Get the audit event
            AuditEvent object = optObject.get();

            // Return the audit event
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
