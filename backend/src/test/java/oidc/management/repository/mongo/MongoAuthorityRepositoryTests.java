package oidc.management.repository.mongo;

import oidc.management.model.mongo.MongoAuthority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MongoAuthorityRepositoryTests {

    @Spy
    private MongoAuthorityRepository mongoAuthorityRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(MongoAuthority.builder().getClass(), mongoAuthorityRepository.entityBuilder().getClass());
    }

}
