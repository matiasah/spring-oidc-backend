package oidc.management.repository.jpa;

import oidc.management.model.jpa.JpaScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JpaScopeRepositoryTests {

    @Spy
    private JpaScopeRepository jpaScopeRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(JpaScope.builder().getClass(), jpaScopeRepository.entityBuilder().getClass());
    }

}
