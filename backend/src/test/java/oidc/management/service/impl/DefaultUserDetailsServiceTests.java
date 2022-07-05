package oidc.management.service.impl;

import oidc.management.service.UserAccountService;
import oidc.management.test.TestAuthority;
import oidc.management.test.TestUserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = {
        DefaultUserDetailsService.class
})
public class DefaultUserDetailsServiceTests {

    @Autowired
    private DefaultUserDetailsService defaultUserDetailsService;

    @MockBean
    private UserAccountService userAccountService;

    @Test
    public void testLoadUserByUsername() {

        // Create TestAuthority
        TestAuthority authority = TestAuthority.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Create TestUserAccount
        TestUserAccount userAccount = TestUserAccount.builder()
                .username(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .authorities(List.of(authority))
                .build();

        // Mock findByUsername
        Mockito.when(userAccountService.findByUsername(userAccount.getUsername())).thenReturn(Optional.of(userAccount));

        // Test loadUserByUsername
        Assertions.assertEquals(userAccount, defaultUserDetailsService.loadUserByUsername(userAccount.getUsername()));

    }

    @Test
    public void testLoadUserByUsernameWithEmpty() {

        // Mock findByUsername
        Mockito.when(userAccountService.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());

        // Test loadUserByUsername
        Assertions.assertThrows(UsernameNotFoundException.class, () -> defaultUserDetailsService.loadUserByUsername("test"));

    }

}
