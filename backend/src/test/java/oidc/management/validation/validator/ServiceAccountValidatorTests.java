package oidc.management.validation.validator;

import oidc.management.config.ValidationConfig;
import oidc.management.service.ServiceAccountService;
import oidc.management.test.TestServiceAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Optional;
import java.util.UUID;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = {
        ValidationConfig.class
})
public class ServiceAccountValidatorTests {

    @Autowired
    private SpringValidatorAdapter validator;

    @MockBean
    private ServiceAccountService serviceAccountService;

    @Test
    public void testIsValid() {

        // Create serviceAccount
        TestServiceAccount serviceAccount = TestServiceAccount.builder()
                .clientId(UUID.randomUUID().toString())
                .clientSecret(UUID.randomUUID().toString())
                .build();

        // Mock findByClientId
        Mockito.when(serviceAccountService.findByClientId(serviceAccount.getClientId())).thenReturn(Optional.empty());

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(serviceAccount, "serviceAccount");

        // Validate serviceAccount
        this.validator.validate(serviceAccount, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameClientId() {

        // Create serviceAccount
        TestServiceAccount serviceAccount = TestServiceAccount.builder()
                .id(UUID.randomUUID().toString())
                .clientId(UUID.randomUUID().toString())
                .clientSecret(UUID.randomUUID().toString())
                .build();

        // Mock findByClientId
        Mockito.when(serviceAccountService.findByClientId(serviceAccount.getClientId())).thenReturn(Optional.of(serviceAccount));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(serviceAccount, "serviceAccount");

        // Validate serviceAccount
        this.validator.validate(serviceAccount, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameNameAndDifferentId() {

        // Create serviceAccount
        TestServiceAccount serviceAccount = TestServiceAccount.builder()
                .id(UUID.randomUUID().toString())
                .clientId(UUID.randomUUID().toString())
                .clientSecret(UUID.randomUUID().toString())
                .build();

        // Create serviceAccount
        TestServiceAccount responseServiceAccount = TestServiceAccount.builder()
                .id(UUID.randomUUID().toString())
                .clientId(serviceAccount.getClientId())
                .clientSecret(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(serviceAccountService.findByClientId(serviceAccount.getClientId())).thenReturn(Optional.of(responseServiceAccount));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(serviceAccount, "serviceAccount");

        // Validate serviceAccount
        this.validator.validate(serviceAccount, result);

        // Validate result
        Assertions.assertTrue(result.hasErrors());

    }

    @Test
    public void testIsValidWithoutServiceAccountService() {

        // Mock ServiceAccountValidator instance
        try (MockedConstruction<ServiceAccountValidator> mock = Mockito.mockConstruction(ServiceAccountValidator.class, (serviceAccountValidator, context) -> {

            // Mock isValid
            Mockito.when(serviceAccountValidator.isValid(Mockito.any(), Mockito.any())).then((args) -> {

                // Remove serviceAccountService
                ReflectionTestUtils.setField(serviceAccountValidator, "serviceAccountService", null);

                // Call real method
                return args.callRealMethod();

            });

        })) {

            // Create serviceAccount
            TestServiceAccount serviceAccount = TestServiceAccount.builder()
                    .clientId(UUID.randomUUID().toString())
                    .clientSecret(UUID.randomUUID().toString())
                    .build();

            // Mock findByClientId
            Mockito.when(serviceAccountService.findByClientId(serviceAccount.getClientId())).thenReturn(Optional.empty());

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(serviceAccount, "serviceAccount");

            // Validate serviceAccount
            this.validator.validate(serviceAccount, result);

            // Validate result
            Assertions.assertFalse(result.hasErrors());

        }

    }

}
