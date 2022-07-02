package oidc.management.repository.elasticsearch;

import oidc.management.model.elasticsearch.ElasticsearchUserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ElasticsearchUserAccountRepositoryTests {

    @Spy
    private ElasticsearchUserAccountRepository elasticsearchUserAccountRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(ElasticsearchUserAccount.builder().getClass(), elasticsearchUserAccountRepository.entityBuilder().getClass());
    }

}
