package oidc.management.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AuthorityTests {

    @Spy
    private Authority authority;

    @Test
    public void testGetAuthority() {
        // Random name
        final String name = UUID.randomUUID().toString();

        // Mock getName
        Mockito.when(authority.getName()).thenReturn(name);

        // Test getAuthority
        Assertions.assertEquals(name, authority.getAuthority());
    }

}
