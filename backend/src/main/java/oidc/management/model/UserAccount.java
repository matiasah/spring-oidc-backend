package oidc.management.model;

import java.time.Instant;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * OIDC User model.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see UserDetails
 * @see oidc.management.service.UserAccountService
 */
@Document(collection = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class UserAccount implements UserDetails {

    @Id
    private String id;

    /**
     * The user's alias.
     * DO NOT ENCRYPT THIS FIELD, IT'S USED FOR SEARCHING/FILTERING USER ACCOUNTS.
     **/
    private String alias;

    @DBRef
    private List<Authority> authorities;

    @NotNull
    @Getter(onMethod_ = {@JsonIgnore})
    @Setter(onMethod_ = {@JsonSetter})
    private String password;

    @NotNull
    private String username;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private String firstName;

    private String lastName;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
    
}
