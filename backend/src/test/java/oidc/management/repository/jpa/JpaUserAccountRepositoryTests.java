package oidc.management.repository.jpa;

import oidc.management.model.jpa.JpaUserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JpaUserAccountRepositoryTests {

    @Spy
    private JpaUserAccountRepository jpaUserAccountRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(JpaUserAccount.builder().getClass(), jpaUserAccountRepository.entityBuilder().getClass());
    }

}
