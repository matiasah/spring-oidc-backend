package oidc.management.repository.elasticsearch;

import oidc.management.model.elasticsearch.ElasticsearchAuthority;
import oidc.management.model.elasticsearch.ElasticsearchScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ElasticsearchScopeRepositoryTests {

    @Spy
    private ElasticsearchScopeRepository elasticsearchScopeRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(ElasticsearchScope.builder().getClass(), elasticsearchScopeRepository.entityBuilder().getClass());
    }

}
