package oidc.management.repository.jpa;

import oidc.management.model.jpa.JpaServiceAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JpaServiceAccountRepositoryTests {

    @Spy
    private JpaServiceAccountRepository jpaServiceAccountRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(JpaServiceAccount.builder().getClass(), jpaServiceAccountRepository.entityBuilder().getClass());
    }

}
