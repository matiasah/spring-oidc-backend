package oidc.management.properties;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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
    private String clientId = UUID.randomUUID().toString();
    private String clientSecret = UUID.randomUUID().toString();
    private Set<String> redirectUris = new HashSet<>(Arrays.asList("http://127.0.0.1/oauth/callback"));
}
