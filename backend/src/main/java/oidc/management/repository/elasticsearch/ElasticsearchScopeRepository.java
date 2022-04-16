package oidc.management.repository.elasticsearch;

import oidc.management.model.Scope;
import oidc.management.model.elasticsearch.ElasticsearchScope;
import oidc.management.repository.ScopeRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@ConditionalOnBean(ElasticsearchRepositoriesAutoConfiguration.class)
public interface ElasticsearchScopeRepository extends ScopeRepository<ElasticsearchScope>, ElasticsearchRepository<ElasticsearchScope, String> {

    @Override
    public default Scope.ScopeBuilder entityBuilder() {
        return ElasticsearchScope.builder();
    }

}
