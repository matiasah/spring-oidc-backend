package oidc.management.repository.jpa;

import oidc.management.model.Authority;
import oidc.management.model.jpa.JpaAuthority;
import oidc.management.repository.AuthorityRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA implementation of the {@link AuthorityRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 14-04-2022
 */
@ConditionalOnBean(JpaRepositoriesAutoConfiguration.class)
public interface JpaAuthorityRepository extends AuthorityRepository<JpaAuthority>, JpaRepository<JpaAuthority, String> {

    @Override
    public default Authority.AuthorityBuilder entityBuilder() {
        return JpaAuthority.builder();
    }

}
