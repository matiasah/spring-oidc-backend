package oidc.management.model.elasticsearch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import oidc.management.model.Authority;
import oidc.management.model.UserAccount;
import oidc.management.validation.annotations.ValidUserAccount;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@Document(indexName = "user_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidUserAccount
public class ElasticsearchUserAccount implements UserAccount<ElasticsearchAuthority> {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;

    private List<ElasticsearchAuthority> authorities;

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

    public static class ElasticsearchUserAccountBuilder implements UserAccountBuilder<ElasticsearchAuthority> {

    }

}
