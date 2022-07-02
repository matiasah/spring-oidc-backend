package oidc.management.repository.mongo;

import oidc.management.model.mongo.MongoServiceAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MongoServiceAccountRepositoryTests {

    @Spy
    private MongoServiceAccountRepository mongoServiceAccountRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(MongoServiceAccount.builder().getClass(), mongoServiceAccountRepository.entityBuilder().getClass());
    }


}
