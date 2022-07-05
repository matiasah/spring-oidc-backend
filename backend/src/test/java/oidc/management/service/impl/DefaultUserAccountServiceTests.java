package oidc.management.service.impl;

import oidc.management.model.UserAccount;
import oidc.management.repository.UserAccountRepository;
import oidc.management.service.UserAccountService;
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
        DefaultUserAccountService.class,
        DefaultUserAccountEncryptionService.class
})
@DataJpaTest
public class DefaultUserAccountServiceTests {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    public void testEntityBuilder() {

        // Test entityBuilder
        Assertions.assertNotNull(userAccountService.entityBuilder());

    }

    @Test
    public void testFindAll() {

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .build();

        // Save userAccount
        userAccountService.save(userAccount);

        // Test findAll
        List<UserAccount> userAccountList = userAccountService.findAll();

        // Validate userAccount list size
        Assertions.assertEquals(1, userAccountList.size());

        // Validate userAccount
        Assertions.assertEquals(userAccount.getUsername(), userAccountList.get(0).getUsername());
        Assertions.assertEquals(userAccount.getPassword(), userAccountList.get(0).getPassword());

    }

    @Test
    public void testFindAllPage() {

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .tags(Set.of("%test%"))
                .build();

        // Save userAccount
        userAccountService.save(userAccount);

        // Test findAll
        Page<UserAccount> userAccountPage = userAccountService.findAll(Pageable.ofSize(5), "test");

        // Convert to list
        List<UserAccount> userAccountList = userAccountPage.toList();

        // Validate userAccount page size
        Assertions.assertEquals(1, userAccountPage.getNumberOfElements());

        // Validate userAccount
        Assertions.assertEquals(userAccount.getUsername(), userAccountList.get(0).getUsername());
        Assertions.assertEquals(userAccount.getPassword(), userAccountList.get(0).getPassword());

    }

    @Test
    public void testFindAllPageEmptySearch() {

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .build();

        // Save userAccount
        userAccountService.save(userAccount);

        // Test findAll
        Page<UserAccount> userAccountPage = userAccountService.findAll(Pageable.ofSize(5), "");

        // Convert to list
        List<UserAccount> userAccountList = userAccountPage.toList();

        // Validate userAccount page size
        Assertions.assertEquals(1, userAccountPage.getNumberOfElements());

        // Validate userAccount
        Assertions.assertEquals(userAccount.getUsername(), userAccountList.get(0).getUsername());
        Assertions.assertEquals(userAccount.getPassword(), userAccountList.get(0).getPassword());

    }

    @Test
    public void testFindById() {

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .build();

        // Save userAccount
        userAccountService.save(userAccount);

        // Test findById
        Optional<UserAccount> optResult = userAccountService.findById(userAccount.getId());

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals(userAccount.getUsername(), optResult.get().getUsername());
        Assertions.assertEquals(userAccount.getPassword(), optResult.get().getPassword());

    }

    @Test
    public void testFindByUsername() {

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .build();

        // Save userAccount
        userAccountService.save(userAccount);

        // Test findById
        Optional<UserAccount> optResult = userAccountService.findByUsername("test");

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals(userAccount.getUsername(), optResult.get().getUsername());
        Assertions.assertEquals(userAccount.getPassword(), optResult.get().getPassword());

    }

    @Test
    public void testSave() {

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .build();

        // Save userAccount
        Assertions.assertNotNull(userAccountService.save(userAccount));

    }

    @Test
    public void testSaveException() {

        // Spy userAccountRepository
        final UserAccountRepository userAccountRepository = Mockito.mock(UserAccountRepository.class, AdditionalAnswers.delegatesTo(this.userAccountRepository));

        // Spy userAccountService
        final UserAccountService userAccountService = Mockito.spy(this.userAccountService);

        // Set userAccountRepository
        ReflectionTestUtils.setField(userAccountService, "userAccountRepository", userAccountRepository);

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .build();

        // Mock save
        Mockito.when(userAccountRepository.save(userAccount)).thenThrow(new DataAccessResourceFailureException("test"));

        // Save userAccount
        Assertions.assertNotNull(userAccountService.save(userAccount));

    }

    @Test
    public void testDeleteById() {

        // Create test userAccount
        UserAccount userAccount = userAccountService.entityBuilder()
                .username("test")
                .password("test")
                .build();

        // Save userAccount
        userAccountService.save(userAccount);

        // Test deleteById
        userAccountService.deleteById(userAccount.getId());

    }

    @Test
    public void testDeleteByIdException() {

        // Spy userAccountRepository
        final UserAccountRepository userAccountRepository = Mockito.mock(UserAccountRepository.class, AdditionalAnswers.delegatesTo(this.userAccountRepository));

        // Spy userAccountService
        final UserAccountService userAccountService = Mockito.spy(this.userAccountService);

        // Set userAccountRepository
        ReflectionTestUtils.setField(userAccountService, "userAccountRepository", userAccountRepository);

        // Mock deleteById
        Mockito.doThrow(new DataAccessResourceFailureException("test")).when(userAccountRepository).deleteById("test");

        // Test deleteById
        userAccountService.deleteById("test");

    }

}
