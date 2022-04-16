package oidc.management.model.jpa;

import lombok.*;
import oidc.management.model.Authority;
import oidc.management.repository.AuthorityRepository;
import oidc.management.validation.annotations.ValidAuthority;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Pageable;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidAuthority
public class JpaAuthority implements Authority {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
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
    @ElementCollection
    private Set<String> tags;

    public static class JpaAuthorityBuilder implements Authority.AuthorityBuilder {

    }

}
