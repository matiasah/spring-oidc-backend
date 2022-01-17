package oidc.management.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.User;

/**
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 * @see User
 * @see UserRepository
 * @see UserRepository#findByUsername(String)
 */
public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findByUsername(String username);
    
}
