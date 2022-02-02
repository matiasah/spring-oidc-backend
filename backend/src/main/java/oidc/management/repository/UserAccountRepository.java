package oidc.management.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.UserAccount;

/**
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 * @see UserAccount
 * @see UserAccountRepository
 * @see UserAccountRepository#findByUsername(String)
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

    public Optional<UserAccount> findByUsername(String username);
    
}
