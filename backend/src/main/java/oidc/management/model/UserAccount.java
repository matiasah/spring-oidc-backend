package oidc.management.model;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import oidc.management.validation.annotations.ValidUserAccount;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * OIDC User model.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see UserDetails
 * @see Authority
 * @see oidc.management.service.UserAccountService
 */
public interface UserAccount<A extends Authority> extends UserDetails {

    public String getId();

    public void setId(String id);

    public List<A> getAuthorities();

    public void setAuthorities(List<A> authorities);

    public String getPassword();

    public void setPassword(String password);

    public String getUsername();

    public void setUsername(String username);

    public String getHashedUsername();

    public void setHashedUsername(String hashedUsername);

    public boolean isAccountNonExpired();

    public void setAccountNonExpired(boolean accountNonExpired);

    public boolean isAccountNonLocked();

    public void setAccountNonLocked(boolean accountNonLocked);

    public boolean isCredentialsNonExpired();

    public void setCredentialsNonExpired(boolean credentialsNonExpired);

    public boolean isEnabled();

    public void setEnabled(boolean enabled);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public Set<String> getTags();

    public void setTags(Set<String> tags);

    public Instant getCreatedAt();

    public void setCreatedAt(Instant createdAt);

    public Instant getUpdatedAt();

    public void setUpdatedAt(Instant updatedAt);

    public interface UserAccountBuilder<A> {

        public UserAccountBuilder id(String id);

        public UserAccountBuilder authorities(List<A> authorities);

        public UserAccountBuilder password(String password);

        public UserAccountBuilder username(String username);

        public UserAccountBuilder hashedUsername(String hashedUsername);

        public UserAccountBuilder accountNonExpired(boolean accountNonExpired);

        public UserAccountBuilder accountNonLocked(boolean accountNonLocked);

        public UserAccountBuilder credentialsNonExpired(boolean credentialsNonExpired);

        public UserAccountBuilder enabled(boolean enabled);

        public UserAccountBuilder firstName(String firstName);

        public UserAccountBuilder lastName(String lastName);

        public UserAccountBuilder tags(Set<String> tags);

        public UserAccountBuilder createdAt(Instant createdAt);

        public UserAccountBuilder updatedAt(Instant updatedAt);

        public UserAccount build();

    }
    
}
