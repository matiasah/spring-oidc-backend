package oidc.management.properties;

import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
import oidc.management.util.DefaultServiceAccountSeeder;

/**
 * Default service account properties.
 * 
 * @author Matias Hermosilla
 * @since 20-02-2022
 * @see DefaultServiceAccountSeeder
 */
@Data
@ConfigurationProperties(prefix = "oidc.management.service-account")
public class DefaultServiceAccountProperties {
    private Boolean enabled;
    private String clientId;
    private String clientSecret;
    private Set<String> redirectUris;
}
