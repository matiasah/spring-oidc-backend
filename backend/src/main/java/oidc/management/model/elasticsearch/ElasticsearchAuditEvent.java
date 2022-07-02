package oidc.management.model.elasticsearch;

import lombok.*;
import oidc.management.model.AuditEvent;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Builder(toBuilder = true)
@Document(indexName = "audit_event")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class ElasticsearchAuditEvent implements AuditEvent {

    @Id
    private String id;

    @NotNull
    private String description;

    @CreatedDate
    private ZonedDateTime createdAt;

    public static class ElasticsearchAuditEventBuilder implements AuditEventBuilder {

    }

}
