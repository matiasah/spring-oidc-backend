package oidc.management.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import oidc.management.model.UserAccount;
import oidc.management.validation.annotations.ValidUserAccount;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidUserAccount
public class JpaUserAccount implements UserAccount<JpaAuthority> {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;

    @Reference
    @OneToMany
    private List<JpaAuthority> authorities;

    @NotNull
    @Getter(onMethod_ = {@JsonIgnore})
    @Setter(onMethod_ = {@JsonSetter})
    private String password;

    @NotNull
    private String username;

    @JsonIgnore
    private String hashedUsername;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private String firstName;

    private String lastName;

    /**
     * The user's tags
     * DO NOT ENCRYPT THIS FIELD, IT'S USED FOR SEARCHING/FILTERING USER ACCOUNTS.
     *
     * @see {@link oidc.management.repository.UserAccountRepository#findByTagsContainingIgnoreCase(String, Pageable)}
     **/
    @ElementCollection
    private Set<String> tags;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public static class JpaUserAccountBuilder implements UserAccount.UserAccountBuilder<JpaAuthority> {

    }

}
