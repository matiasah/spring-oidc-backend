package oidc.management.service.impl;

import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import oidc.management.service.ServiceAccountService;
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
        DefaultServiceAccountService.class,
        DefaultServiceAccountEncryptionService.class
})
@DataJpaTest
public class DefaultServiceAccountServiceTests {

    @Autowired
    private ServiceAccountService serviceAccountService;

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    @Test
    public void testEntityBuilder() {

        // Test entityBuilder
        Assertions.assertNotNull(serviceAccountService.entityBuilder());

    }

    @Test
    public void testFindAll() {

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .build();

        // Save serviceAccount
        serviceAccountService.save(serviceAccount);

        // Test findAll
        List<ServiceAccount> serviceAccountList = serviceAccountService.findAll();

        // Validate serviceAccount list size
        Assertions.assertEquals(1, serviceAccountList.size());

        // Validate serviceAccount
        Assertions.assertEquals(serviceAccount.getClientId(), serviceAccountList.get(0).getClientId());
        Assertions.assertEquals(serviceAccount.getClientSecret(), serviceAccountList.get(0).getClientSecret());
        Assertions.assertEquals(serviceAccount.getClientDescription(), serviceAccountList.get(0).getClientDescription());

    }

    @Test
    public void testFindAllPage() {

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .tags(Set.of("%test%"))
                .build();

        // Save serviceAccount
        serviceAccountService.save(serviceAccount);

        // Test findAll
        Page<ServiceAccount> serviceAccountPage = serviceAccountService.findAll(Pageable.ofSize(5), "test");

        // Convert to list
        List<ServiceAccount> serviceAccountList = serviceAccountPage.toList();

        // Validate serviceAccount page size
        Assertions.assertEquals(1, serviceAccountPage.getNumberOfElements());

        // Validate serviceAccount
        Assertions.assertEquals(serviceAccount.getClientId(), serviceAccountList.get(0).getClientId());
        Assertions.assertEquals(serviceAccount.getClientSecret(), serviceAccountList.get(0).getClientSecret());
        Assertions.assertEquals(serviceAccount.getClientDescription(), serviceAccountList.get(0).getClientDescription());

    }

    @Test
    public void testFindAllPageEmptySearch() {

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .build();

        // Save serviceAccount
        serviceAccountService.save(serviceAccount);

        // Test findAll
        Page<ServiceAccount> serviceAccountPage = serviceAccountService.findAll(Pageable.ofSize(5), "");

        // Convert to list
        List<ServiceAccount> serviceAccountList = serviceAccountPage.toList();

        // Validate serviceAccount page size
        Assertions.assertEquals(1, serviceAccountPage.getNumberOfElements());

        // Validate serviceAccount
        Assertions.assertEquals(serviceAccount.getClientId(), serviceAccountList.get(0).getClientId());
        Assertions.assertEquals(serviceAccount.getClientSecret(), serviceAccountList.get(0).getClientSecret());
        Assertions.assertEquals(serviceAccount.getClientDescription(), serviceAccountList.get(0).getClientDescription());

    }

    @Test
    public void testFindById() {

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .build();

        // Save serviceAccount
        serviceAccountService.save(serviceAccount);

        // Test findById
        Optional<ServiceAccount> optResult = serviceAccountService.findById(serviceAccount.getId());

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals(serviceAccount.getClientId(), optResult.get().getClientId());
        Assertions.assertEquals(serviceAccount.getClientSecret(), optResult.get().getClientSecret());
        Assertions.assertEquals(serviceAccount.getClientDescription(), optResult.get().getClientDescription());

    }

    @Test
    public void testFindByClientId() {

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .build();

        // Save serviceAccount
        serviceAccountService.save(serviceAccount);

        // Test findById
        Optional<ServiceAccount> optResult = serviceAccountService.findByClientId(serviceAccount.getClientId());

        // Validate
        Assertions.assertEquals(true, optResult.isPresent());

        Assertions.assertEquals(serviceAccount.getClientId(), optResult.get().getClientId());
        Assertions.assertEquals(serviceAccount.getClientSecret(), optResult.get().getClientSecret());
        Assertions.assertEquals(serviceAccount.getClientDescription(), optResult.get().getClientDescription());

    }

    @Test
    public void testSave() {

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .build();

        // Save serviceAccount
        Assertions.assertNotNull(serviceAccountService.save(serviceAccount));

    }

    @Test
    public void testSaveException() {

        // Spy serviceAccountRepository
        final ServiceAccountRepository serviceAccountRepository = Mockito.mock(ServiceAccountRepository.class, AdditionalAnswers.delegatesTo(this.serviceAccountRepository));

        // Spy serviceAccountService
        final ServiceAccountService serviceAccountService = Mockito.spy(this.serviceAccountService);

        // Set serviceAccountRepository
        ReflectionTestUtils.setField(serviceAccountService, "serviceAccountRepository", serviceAccountRepository);

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .build();

        // Mock save
        Mockito.when(serviceAccountRepository.save(serviceAccount)).thenThrow(new DataAccessResourceFailureException("test"));

        // Save serviceAccount
        Assertions.assertNotNull(serviceAccountService.save(serviceAccount));

    }

    @Test
    public void testDeleteById() {

        // Create test serviceAccount
        ServiceAccount serviceAccount = serviceAccountService.entityBuilder()
                .clientId("test")
                .clientSecret("test")
                .clientDescription("test")
                .build();

        // Save serviceAccount
        serviceAccountService.save(serviceAccount);

        // Test deleteById
        serviceAccountService.deleteById(serviceAccount.getId());

    }

    @Test
    public void testDeleteByIdException() {

        // Spy serviceAccountRepository
        final ServiceAccountRepository serviceAccountRepository = Mockito.mock(ServiceAccountRepository.class, AdditionalAnswers.delegatesTo(this.serviceAccountRepository));

        // Spy serviceAccountService
        final ServiceAccountService serviceAccountService = Mockito.spy(this.serviceAccountService);

        // Set serviceAccountRepository
        ReflectionTestUtils.setField(serviceAccountService, "serviceAccountRepository", serviceAccountRepository);

        // Mock deleteById
        Mockito.doThrow(new DataAccessResourceFailureException("test")).when(serviceAccountRepository).deleteById("test");

        // Test deleteById
        serviceAccountService.deleteById("test");

    }

}
