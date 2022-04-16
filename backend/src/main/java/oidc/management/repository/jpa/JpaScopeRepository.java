package oidc.management.repository.jpa;

import oidc.management.model.Scope;
import oidc.management.model.jpa.JpaScope;
import oidc.management.repository.ScopeRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA implementation of the {@link ScopeRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 14-04-2022
 */
@ConditionalOnBean(JpaRepositoriesAutoConfiguration.class)
public interface JpaScopeRepository extends ScopeRepository<JpaScope>, JpaRepository<JpaScope, String> {

    @Override
    public default Scope.ScopeBuilder entityBuilder() {
        return JpaScope.builder();
    }

}
