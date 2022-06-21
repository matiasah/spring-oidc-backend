package oidc.management.repository.jpa;

import oidc.management.model.jpa.JpaAuditEvent;
import oidc.management.repository.AuditEventRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA implementation of {@link AuditEventRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 02-05-2022
 */
@ConditionalOnBean(JpaRepositoriesAutoConfiguration.class)
public interface JpaAuditEventRepository extends AuditEventRepository<JpaAuditEvent>, JpaRepository<JpaAuditEvent, String> {

    @Override
    public default JpaAuditEvent.JpaAuditEventBuilder entityBuilder() {
        return JpaAuditEvent.builder();
    }

}
