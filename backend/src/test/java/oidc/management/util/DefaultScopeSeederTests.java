package oidc.management.util;

import oidc.management.model.Scope;
import oidc.management.service.ScopeService;
import oidc.management.test.TestScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DefaultScopeSeederTests {

    private static final Set<String> DEFAULT_SCOPES_TAGS = Set.of("default");

    @InjectMocks
    private DefaultScopeSeeder defaultScopeSeeder;

    @Mock
    private ScopeService scopeService;

    @Test
    public void testGetDefaultScopes() {

        // Mock entityBuilder
        Mockito.when(scopeService.entityBuilder()).thenReturn(TestScope.builder());

        // Test getDefaultScopes
        List<Scope> scopes = defaultScopeSeeder.getDefaultScopes();

        // Validate scopes
        Assertions.assertNotNull(scopes);

        // Validate scopes length
        Assertions.assertNotEquals(0, scopes.size());

        // For every scope
        for (Scope scope : scopes) {

            // Validate tags
            Assertions.assertTrue(scope.getTags().containsAll(DEFAULT_SCOPES_TAGS));
            Assertions.assertTrue(DEFAULT_SCOPES_TAGS.containsAll(scope.getTags()));

        }

    }

    @Test
    public void testSeed() {

        // Spy defaultScopeSeeder
        DefaultScopeSeeder defaultScopeSeeder = Mockito.spy(this.defaultScopeSeeder);

        // Mock entityBuilder
        Mockito.when(scopeService.entityBuilder()).thenReturn(TestScope.builder());

        // Do nothing
        Mockito.doNothing().when(defaultScopeSeeder).createIfNotExists(Mockito.any(TestScope.class));

        // Test seed
        defaultScopeSeeder.seed();

    }

    @Test
    public void testCreateIfNotExists() {

        // Create TestScope
        TestScope scope = TestScope.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(scopeService.findByName(scope.getName())).thenReturn(Optional.empty());

        // Test createIfNotExists
        defaultScopeSeeder.createIfNotExists(scope);

    }

    @Test
    public void testCreateIfNotExistsWithPresent() {

        // Create TestScope
        TestScope scope = TestScope.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(scopeService.findByName(scope.getName())).thenReturn(Optional.of(scope));

        // Test createIfNotExists
        defaultScopeSeeder.createIfNotExists(scope);

    }

}
