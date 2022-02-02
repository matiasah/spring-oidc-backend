package oidc.management.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OIDC User model.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see UserDetails
 * @see UserAccountRepository
 */
@Document(collection = "users")
@Data
@EqualsAndHashCode(of = "id")
public class UserAccount implements UserDetails {
    private String id;
    private List<GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
