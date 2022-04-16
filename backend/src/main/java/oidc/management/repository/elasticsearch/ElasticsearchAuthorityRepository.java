package oidc.management.repository.elasticsearch;

import oidc.management.model.Authority;
import oidc.management.model.elasticsearch.ElasticsearchAuthority;
import oidc.management.repository.AuthorityRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@ConditionalOnBean(ElasticsearchRepositoriesAutoConfiguration.class)
public interface ElasticsearchAuthorityRepository extends AuthorityRepository<ElasticsearchAuthority>, ElasticsearchRepository<ElasticsearchAuthority, String> {

    @Override
    public default Authority.AuthorityBuilder entityBuilder() {
        return ElasticsearchAuthority.builder();
    }

}
