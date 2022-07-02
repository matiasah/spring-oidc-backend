package oidc.management.repository.mongo;

import oidc.management.model.mongo.MongoUserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MongoUserAccountRepositoryTests {

    @Spy
    private MongoUserAccountRepository mongoUserAccountRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(MongoUserAccount.builder().getClass(), mongoUserAccountRepository.entityBuilder().getClass());
    }

}
