package oidc.management.model;

import org.springframework.security.core.GrantedAuthority;
import oidc.management.repository.AuthorityRepository;

import java.util.Set;

/**
 * Spring Security Authority model.
 * 
 * @author Matías Hermosilla
 * @since 02-02-2022
 * @see GrantedAuthority
 * @see AuthorityRepository
 */
public interface Authority extends GrantedAuthority {

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String getHashedName();

    public void setHashedName(String hashedName);

    public String getDescription();

    public void setDescription(String description);

    @Override
    public default String getAuthority() {
        return getName();
    }

    public Set<String> getTags();

    public void setTags(Set<String> tags);

    public interface AuthorityBuilder {

        public AuthorityBuilder id(String id);

        public AuthorityBuilder name(String name);

        public AuthorityBuilder hashedName(String hashedName);

        public AuthorityBuilder description(String description);

        public AuthorityBuilder tags(Set<String> tags);

        public Authority build();

    }
    
}
