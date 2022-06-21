package oidc.management.repository.mongo;

import oidc.management.model.mongo.MongoAuditEvent;
import oidc.management.repository.AuditEventRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongo implementation of the {@link AuditEventRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 02-05-2022
 */
@ConditionalOnBean(MongoRepositoriesAutoConfiguration.class)
public interface MongoAuditEventRepository extends AuditEventRepository<MongoAuditEvent>, MongoRepository<MongoAuditEvent, String> {

    @Override
    public default MongoAuditEvent.MongoAuditEventBuilder entityBuilder() {
        return MongoAuditEvent.builder();
    }

}
