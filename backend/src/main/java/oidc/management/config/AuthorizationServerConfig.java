package oidc.management.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL;

/**
 * OIDC Authorization Server Configuration.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see OAuth2AuthorizationServerConfiguration
 * @see OAuth2AuthorizationServerConfigurer
 * @see SecurityFilterChain
 * @see HttpSecurity
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

    @Autowired
    private CorsConfigurationSource configurationSource;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
			new OAuth2AuthorizationServerConfigurer();

		authorizationServerConfigurer
			.authorizationEndpoint(authorizationEndpoint ->
				authorizationEndpoint.consentPage("/consent"));

		RequestMatcher endpointsMatcher = authorizationServerConfigurer
			.getEndpointsMatcher();
        
		return http
			.securityMatcher(endpointsMatcher)
			.authorizeHttpRequests()
				.anyRequest()
					.authenticated()
					.and()
			.csrf()
				.ignoringRequestMatchers(endpointsMatcher)
				.and()
			.cors()
				.configurationSource(configurationSource)
				.and()
			.apply(authorizationServerConfigurer)
				.and()
			.formLogin(Customizer.withDefaults())
				.exceptionHandling()
					.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(DEFAULT_LOGIN_PAGE_URL))
					.and()
				.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
	public OAuth2AuthorizationConsentService authorizationConsentService() {
		// Will be used by the ConsentController
		return new InMemoryOAuth2AuthorizationConsentService();
	}

}
