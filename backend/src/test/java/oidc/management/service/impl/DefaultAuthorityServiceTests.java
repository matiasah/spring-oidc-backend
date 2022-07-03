package oidc.management.service.impl;

import oidc.management.model.Authority;
import oidc.management.repository.AuthorityRepository;
import oidc.management.service.AuthorityService;
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
        DefaultAuthorityService.class,
        DefaultAuthorityEncryptionService.class
})
@DataJpaTest
public class DefaultAuthorityServiceTests {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void testEntityBuilder() {

        // Test entityBuilder
        Assertions.assertNotNull(authorityService.entityBuilder());

    }

    @Test
    public void testFindAll() {

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save authority
        authorityService.save(authority);

        // Test findAll
        List<Authority> authorityList = authorityService.findAll();

        // Validate authorities size
        Assertions.assertEquals(1, authorityList.size());

        // Validate authority
        Assertions.assertEquals("test", authorityList.get(0).getName());
        Assertions.assertEquals("test", authorityList.get(0).getDescription());

    }

    @Test
    public void testFindAllPage() {

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .tags(Set.of("%test%"))
                .build();

        // Save authority
        authorityService.save(authority);

        // Test findAll
        Page<Authority> authorityPage = authorityService.findAll(Pageable.ofSize(5), "test");

        // Convert to list
        List<Authority> authorityList = authorityPage.toList();

        // Validate authorities size
        Assertions.assertEquals(1, authorityPage.getNumberOfElements());

        // Validate authority
        Assertions.assertEquals("test", authorityList.get(0).getName());
        Assertions.assertEquals("test", authorityList.get(0).getDescription());

    }

    @Test
    public void testFindAllPageEmptySearch() {

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save authority
        authorityService.save(authority);

        // Test findAll
        Page<Authority> authorityPage = authorityService.findAll(Pageable.ofSize(5), "");

        // Convert to list
        List<Authority> authorityList = authorityPage.toList();

        // Validate authorities size
        Assertions.assertEquals(1, authorityPage.getNumberOfElements());

        // Validate authority
        Assertions.assertEquals("test", authorityList.get(0).getName());
        Assertions.assertEquals("test", authorityList.get(0).getDescription());

    }

    @Test
    public void testFindById() {

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save authority
        authorityService.save(authority);

        // Test findById
        Optional<Authority> optResult = authorityService.findById(authority.getId());

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals("test", optResult.get().getName());
        Assertions.assertEquals("test", optResult.get().getDescription());

    }

    @Test
    public void testFindByName() {

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save authority
        authorityService.save(authority);

        // Test findById
        Optional<Authority> optResult = authorityService.findByName("test");

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals("test", optResult.get().getName());
        Assertions.assertEquals("test", optResult.get().getDescription());

    }

    @Test
    public void testSave() {

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save authority
        Assertions.assertNotNull(authorityService.save(authority));

    }

    @Test
    public void testSaveException() {

        // Spy authorityRepository
        final AuthorityRepository authorityRepository = Mockito.mock(AuthorityRepository.class, AdditionalAnswers.delegatesTo(this.authorityRepository));

        // Spy authorityService
        final AuthorityService authorityService = Mockito.spy(this.authorityService);

        // Set authorityRepository
        ReflectionTestUtils.setField(authorityService, "authorityRepository", authorityRepository);

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Mock save
        Mockito.when(authorityRepository.save(authority)).thenThrow(new DataAccessResourceFailureException("test"));

        // Save authority
        Assertions.assertNotNull(authorityService.save(authority));

    }

    @Test
    public void testDeleteById() {

        // Create test Authority
        Authority authority = authorityService.entityBuilder()
                .name("test")
                .description("test")
                .build();

        // Save authority
        authorityService.save(authority);

        // Test deleteById
        authorityService.deleteById(authority.getId());

    }

    @Test
    public void testDeleteByIdException() {

        // Spy authorityRepository
        final AuthorityRepository authorityRepository = Mockito.mock(AuthorityRepository.class, AdditionalAnswers.delegatesTo(this.authorityRepository));

        // Spy authorityService
        final AuthorityService authorityService = Mockito.spy(this.authorityService);

        // Set authorityRepository
        ReflectionTestUtils.setField(authorityService, "authorityRepository", authorityRepository);

        // Mock deleteById
        Mockito.doThrow(new DataAccessResourceFailureException("test")).when(authorityRepository).deleteById("test");

        // Test deleteById
        authorityService.deleteById("test");

    }

}
