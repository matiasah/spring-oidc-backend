package oidc.management.properties;

import java.util.UUID;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * Default properties for the user account.
 * 
 * @author Matias Hermosilla
 * @since 08-03-2022
 */
@Data
@ConfigurationProperties(prefix = "oidc.management.user-account")
public class DefaultUserAccountProperties {
    private Boolean enabled;
    private String username = UUID.randomUUID().toString();
    private String password = UUID.randomUUID().toString();
}
