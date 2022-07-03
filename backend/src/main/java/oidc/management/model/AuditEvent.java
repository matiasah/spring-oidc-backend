package oidc.management.model;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * Entity for auditing authentication events
 *
 * @author Matías Hermosilla
 * @since 02-05-2022
 */
public interface AuditEvent {

    public String getId();

    public void setId(String id);

    public String getDescription();

    public void setDescription(@NotNull String description);

    public ZonedDateTime getCreatedAt();

    public void setCreatedAt(ZonedDateTime createdAt);

    public interface AuditEventBuilder {

        public AuditEventBuilder id(String id);

        public AuditEventBuilder description(String description);

        public AuditEvent build();

    }

}
