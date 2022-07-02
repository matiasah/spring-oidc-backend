package oidc.management.repository.elasticsearch;

import oidc.management.model.elasticsearch.ElasticsearchAuthority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ElasticsearchAuthorityRepositoryTests {

    @Spy
    private ElasticsearchAuthorityRepository elasticsearchAuthorityRepository;

    @Test
    public void testEntityBuilder() {
        // Test entityBuilder
        Assertions.assertEquals(ElasticsearchAuthority.builder().getClass(), elasticsearchAuthorityRepository.entityBuilder().getClass());
    }

}
