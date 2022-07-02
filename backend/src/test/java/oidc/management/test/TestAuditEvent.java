package oidc.management.test;

import lombok.*;
import oidc.management.model.AuditEvent;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class TestAuditEvent implements AuditEvent {

    private String id;

    @NotNull
    private String description;

    @CreatedDate
    private ZonedDateTime createdAt;

    public static class TestAuditEventBuilder implements AuditEvent.AuditEventBuilder {

    }

}
