package oidc.management.model;

import lombok.*;
import oidc.management.validation.annotations.ValidAuthority;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import oidc.management.repository.AuthorityRepository;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Spring Security Authority model.
 * 
 * @author Matías Hermosilla
 * @since 02-02-2022
 * @see GrantedAuthority
 * @see AuthorityRepository
 */
@Builder
@Document(collection = "authorities")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidAuthority
public class Authority implements GrantedAuthority {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
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
    
}
