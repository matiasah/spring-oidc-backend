package oidc.management.repository.jpa;

import oidc.management.model.jpa.JpaAuditEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JpaAuditEventRepositoryTests {

    @Spy
    private JpaAuditEventRepository jpaAuditEventRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(JpaAuditEvent.builder().getClass(), jpaAuditEventRepository.entityBuilder().getClass());
    }

}
