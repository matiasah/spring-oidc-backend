package oidc.management.repository.elasticsearch;

import oidc.management.model.elasticsearch.ElasticsearchAuditEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ElasticsearchAuditEventRepositoryTests {

    @Spy
    private ElasticsearchAuditEventRepository elasticsearchAuditEventRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(ElasticsearchAuditEvent.builder().getClass(), elasticsearchAuditEventRepository.entityBuilder().getClass());
    }

}
