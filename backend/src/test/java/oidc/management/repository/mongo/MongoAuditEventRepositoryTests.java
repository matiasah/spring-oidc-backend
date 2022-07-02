package oidc.management.repository.mongo;

import oidc.management.model.mongo.MongoAuditEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MongoAuditEventRepositoryTests {

    @Spy
    private MongoAuditEventRepository mongoAuditEventRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(MongoAuditEvent.builder().getClass(), mongoAuditEventRepository.entityBuilder().getClass());
    }

}
