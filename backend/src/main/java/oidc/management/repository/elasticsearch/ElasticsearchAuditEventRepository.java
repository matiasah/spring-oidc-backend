package oidc.management.repository.elasticsearch;

import oidc.management.model.elasticsearch.ElasticsearchAuditEvent;
import oidc.management.repository.AuditEventRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Elasticsearch implementation of {@link AuditEventRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 02-05-2022
 */
@ConditionalOnBean(ElasticsearchRepositoriesAutoConfiguration.class)
public interface ElasticsearchAuditEventRepository extends AuditEventRepository<ElasticsearchAuditEvent>, ElasticsearchRepository<ElasticsearchAuditEvent, String> {

    @Override
    public default ElasticsearchAuditEvent.ElasticsearchAuditEventBuilder entityBuilder() {
        return ElasticsearchAuditEvent.builder();
    }

}
