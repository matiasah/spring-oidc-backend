package oidc.management.service.impl;

import oidc.management.model.Scope;
import oidc.management.repository.ScopeRepository;
import oidc.management.service.ScopeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Import({
        DefaultScopeService.class,
        DefaultScopeEncryptionService.class
})
@DataJpaTest
public class DefaultScopeServiceTests {

    @Autowired
    private ScopeService scopeService;

    @Autowired
    private ScopeRepository scopeRepository;

    @Test
    public void testEntityBuilder() {

        // Test entityBuilder
        Assertions.assertNotNull(scopeService.entityBuilder());

    }

    @Test
    public void testFindAll() {

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save scope
        scopeService.save(scope);

        // Test findAll
        List<Scope> scopeList = scopeService.findAll();

        // Validate scope list size
        Assertions.assertEquals(1, scopeList.size());

        // Validate scope
        Assertions.assertEquals("test", scopeList.get(0).getName());
        Assertions.assertEquals("test", scopeList.get(0).getDescription());

    }

    @Test
    public void testFindAllPage() {

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .tags(Set.of("%test%"))
                .build();

        // Save scope
        scopeService.save(scope);

        // Test findAll
        Page<Scope> scopePage = scopeService.findAll(Pageable.ofSize(5), "test");

        // Convert to list
        List<Scope> scopeList = scopePage.toList();

        // Validate scope page size
        Assertions.assertEquals(1, scopePage.getNumberOfElements());

        // Validate scope
        Assertions.assertEquals("test", scopeList.get(0).getName());
        Assertions.assertEquals("test", scopeList.get(0).getDescription());

    }

    @Test
    public void testFindAllPageEmptySearch() {

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save scope
        scopeService.save(scope);

        // Test findAll
        Page<Scope> scopePage = scopeService.findAll(Pageable.ofSize(5), "");

        // Convert to list
        List<Scope> scopeList = scopePage.toList();

        // Validate scope page size
        Assertions.assertEquals(1, scopePage.getNumberOfElements());

        // Validate scope
        Assertions.assertEquals("test", scopeList.get(0).getName());
        Assertions.assertEquals("test", scopeList.get(0).getDescription());

    }

    @Test
    public void testFindById() {

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save scope
        scopeService.save(scope);

        // Test findById
        Optional<Scope> optResult = scopeService.findById(scope.getId());

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals("test", optResult.get().getName());
        Assertions.assertEquals("test", optResult.get().getDescription());

    }

    @Test
    public void testFindByName() {

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save scope
        scopeService.save(scope);

        // Test findById
        Optional<Scope> optResult = scopeService.findByName("test");

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals("test", optResult.get().getName());
        Assertions.assertEquals("test", optResult.get().getDescription());

    }

    @Test
    public void testSave() {

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save scope
        Assertions.assertNotNull(scopeService.save(scope));

    }

    @Test
    public void testSaveException() {

        // Spy scopeRepository
        final ScopeRepository scopeRepository = Mockito.mock(ScopeRepository.class, AdditionalAnswers.delegatesTo(this.scopeRepository));

        // Spy scopeService
        final ScopeService scopeService = Mockito.spy(this.scopeService);

        // Set scopeRepository
        ReflectionTestUtils.setField(scopeService, "scopeRepository", scopeRepository);

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Mock save
        Mockito.when(scopeRepository.save(scope)).thenThrow(new DataAccessResourceFailureException("test"));

        // Save scope
        Assertions.assertNotNull(scopeService.save(scope));

    }

    @Test
    public void testDeleteById() {

        // Create test scope
        Scope scope = scopeService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save scope
        scopeService.save(scope);

        // Test deleteById
        scopeService.deleteById(scope.getId());

    }

    @Test
    public void testDeleteByIdException() {

        // Spy scopeRepository
        final ScopeRepository scopeRepository = Mockito.mock(ScopeRepository.class, AdditionalAnswers.delegatesTo(this.scopeRepository));

        // Spy scopeService
        final ScopeService scopeService = Mockito.spy(this.scopeService);

        // Set scopeRepository
        ReflectionTestUtils.setField(scopeService, "scopeRepository", scopeRepository);

        // Mock deleteById
        Mockito.doThrow(new DataAccessResourceFailureException("test")).when(scopeRepository).deleteById("test");

        // Test deleteById
        scopeService.deleteById("test");

    }

}
