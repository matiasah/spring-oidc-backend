package oidc.management.repository.jpa;

import oidc.management.model.jpa.JpaAuthority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JpaAuthorityRepositoryTests {

    @Spy
    private JpaAuthorityRepository jpaAuthorityRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(JpaAuthority.builder().getClass(), jpaAuthorityRepository.entityBuilder().getClass());
    }

}
