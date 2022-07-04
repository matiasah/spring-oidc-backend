package oidc.management.service.impl;

import oidc.management.service.ServiceAccountService;
import oidc.management.test.TestAuthority;
import oidc.management.test.TestServiceAccount;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = {
        DefaultTokenCustomizerService.class
})
public class DefaultTokenCustomizerServiceTests {

    @Autowired
    private DefaultTokenCustomizerService defaultTokenCustomizerService;

    @MockBean
    private ServiceAccountService serviceAccountService;

    @Mock
    private Authentication authentication;

    @Mock
    private JwsAlgorithm jwsAlgorithm;

    @Mock
    private RegisteredClient registeredClient;

    @Test
    public void testCustomize() {

        // Create TestAuthority
        TestAuthority testAuthority = TestAuthority.builder()
                .name("test")
                .build();

        // Create a list of granted authorities
        List<TestAuthority> grantedAuthorities = List.of(testAuthority);

        // Cast to List<GrantedAuthority>
        List<GrantedAuthority> grantedAuthorities1 = (List) grantedAuthorities;

        // Testing Authentication
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(authentication, authentication, grantedAuthorities1);

        // Create JwsHeader
        JwsHeader.Builder jwsHeaderBuilder = JwsHeader.with(jwsAlgorithm);

        // Create JwtClaimsSet.Builder
        JwtClaimsSet.Builder jwtClaimsSetBuilder = JwtClaimsSet.builder();

        // Mock getId
        Mockito.when(registeredClient.getId()).thenReturn(UUID.randomUUID().toString());

        // Create JwtEncodingContext
        JwtEncodingContext jwtEncodingContext = JwtEncodingContext.with(jwsHeaderBuilder, jwtClaimsSetBuilder)
                .principal(testingAuthenticationToken)
                .registeredClient(registeredClient)
                .build();

        // Create TestServiceAccount
        TestServiceAccount testServiceAccount = TestServiceAccount.builder()
                .authorities(grantedAuthorities)
                .build();

        // Mock findById
        Mockito.when(serviceAccountService.findById(Mockito.anyString())).thenReturn(Optional.of(testServiceAccount));

        // Test customize
        defaultTokenCustomizerService.customize(jwtEncodingContext);

    }

}
