package oidc.management.model.jpa;

import lombok.*;
import oidc.management.model.AuditEvent;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class JpaAuditEvent implements AuditEvent {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;

    @NotNull
    private String description;

    @CreatedDate
    private ZonedDateTime createdAt;

    public static class JpaAuditEventBuilder implements AuditEventBuilder {

    }

}
