package oidc.management.model.mongo;

import lombok.*;
import oidc.management.model.AuditEvent;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Builder
@Document(collection = "audit_event")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class MongoAuditEvent implements AuditEvent {

    @Id
    private String id;

    @NotNull
    private String description;

    @CreatedDate
    private ZonedDateTime createdAt;

    public static class MongoAuditEventBuilder implements AuditEventBuilder {

    }

}
