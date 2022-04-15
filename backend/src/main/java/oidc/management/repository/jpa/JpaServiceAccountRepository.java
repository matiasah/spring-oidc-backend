package oidc.management.repository.jpa;

import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA implementation of the {@link ServiceAccountRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 14-04-2022
 */
@ConditionalOnBean(JpaRepositoriesAutoConfiguration.class)
public interface JpaServiceAccountRepository extends ServiceAccountRepository, JpaRepository<ServiceAccount, String> {

}
