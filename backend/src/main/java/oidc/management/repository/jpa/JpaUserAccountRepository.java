package oidc.management.repository.jpa;

import oidc.management.model.UserAccount;
import oidc.management.model.jpa.JpaUserAccount;
import oidc.management.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA implementation of the {@link UserAccountRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 14-04-2022
 */
@ConditionalOnBean(JpaRepositoriesAutoConfiguration.class)
public interface JpaUserAccountRepository extends UserAccountRepository<JpaUserAccount>, JpaRepository<JpaUserAccount, String> {

    @Override
    public default UserAccount.UserAccountBuilder entityBuilder() {
        return JpaUserAccount.builder();
    }

}
