package oidc.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oidc.management.validation.annotations.ValidScope;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

/**
 * Spring Security Scope model.
 * 
 * @author Matías Hermosilla
 * @since 13-02-2022
 */
public interface Scope {

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(@NotNull String name);

    public String getHashedName();

    public void setHashedName(String hashedName);

    public String getDescription();

    public void setDescription(String description);

    public Set<String> getTags();

    public void setTags(Set<String> tags);

    public interface ScopeBuilder {

        public ScopeBuilder id(String id);

        public ScopeBuilder name(String name);

        public ScopeBuilder hashedName(String hashedName);

        public ScopeBuilder description(String description);

        public ScopeBuilder tags(Set<String> tags);

        public Scope build();

    }

}
