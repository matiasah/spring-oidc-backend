package oidc.management.test;

import lombok.*;
import oidc.management.model.Authority;
import oidc.management.repository.AuthorityRepository;
import oidc.management.validation.annotations.ValidAuthority;
import org.springframework.data.domain.Pageable;

import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidAuthority
public class TestAuthority implements Authority {

    private String id;

    @NotNull
    private String name;

    /**
     * Persists a hashed version of the authority name.
     *
     * @see {@link oidc.management.service.AuthorityEncryptionService#getHashedName(String)}
     */
    private String hashedName;

    private String description;

    @Override
    public String getAuthority() {
        return name;
    }

    /**
     * The scope's tags
     * DO NOT ENCRYPT THIS FIELD, IT'S USED FOR SEARCHING/FILTERING AUTHORITIES.
     *
     * @see {@link AuthorityRepository#findByTagsContainingIgnoreCase(String, Pageable)}
     **/
    private Set<String> tags;

    public static class TestAuthorityBuilder implements Authority.AuthorityBuilder {

    }

}
