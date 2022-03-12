package oidc.management.properties;

import javax.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import lombok.Data;

/**
 * Default properties for the user account.
 * 
 * @author Matias Hermosilla
 * @since 08-03-2022
 */
@Validated
@Data
@ConfigurationProperties(prefix = "oidc.management.default.user-account")
public class DefaultUserAccountProperties {

    private Boolean enabled;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
