package oidc.management.repository.elasticsearch;

import oidc.management.model.elasticsearch.ElasticsearchServiceAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ElasticsearchServiceAccountRepositoryTests {

    @Spy
    private ElasticsearchServiceAccountRepository elasticsearchServiceAccountRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(ElasticsearchServiceAccount.builder().getClass(), elasticsearchServiceAccountRepository.entityBuilder().getClass());
    }

}
