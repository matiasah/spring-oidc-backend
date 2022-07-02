package oidc.management.repository.mongo;

import oidc.management.model.mongo.MongoScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MongoScopeRepositoryTests {

    @Spy
    private MongoScopeRepository mongoScopeRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(MongoScope.builder().getClass(), mongoScopeRepository.entityBuilder().getClass());
    }

}
