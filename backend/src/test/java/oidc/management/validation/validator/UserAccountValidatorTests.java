package oidc.management.validation.validator;

import oidc.management.config.ValidationConfig;
import oidc.management.service.UserAccountService;
import oidc.management.test.TestUserAccount;
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
public class UserAccountValidatorTests {

    @Autowired
    private SpringValidatorAdapter validator;

    @MockBean
    private UserAccountService userAccountService;

    @Test
    public void testIsValid() {

        // Create userAccount
        TestUserAccount userAccount = TestUserAccount.builder()
                .username(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .build();

        // Mock findByUsername
        Mockito.when(userAccountService.findByUsername(userAccount.getUsername())).thenReturn(Optional.empty());

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(userAccount, "userAccount");

        // Validate userAccount
        this.validator.validate(userAccount, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameUsername() {

        // Create userAccount
        TestUserAccount userAccount = TestUserAccount.builder()
                .id(UUID.randomUUID().toString())
                .username(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .build();

        // Mock findByUsername
        Mockito.when(userAccountService.findByUsername(userAccount.getUsername())).thenReturn(Optional.of(userAccount));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(userAccount, "userAccount");

        // Validate userAccount
        this.validator.validate(userAccount, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameUsernameAndDifferentId() {

        // Create userAccount
        TestUserAccount userAccount = TestUserAccount.builder()
                .id(UUID.randomUUID().toString())
                .username(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .build();

        // Create userAccount
        TestUserAccount responseUserAccount = TestUserAccount.builder()
                .id(UUID.randomUUID().toString())
                .username(userAccount.getUsername())
                .password(UUID.randomUUID().toString())
                .build();

        // Mock findByUsername
        Mockito.when(userAccountService.findByUsername(userAccount.getUsername())).thenReturn(Optional.of(responseUserAccount));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(userAccount, "userAccount");

        // Validate userAccount
        this.validator.validate(userAccount, result);

        // Validate result
        Assertions.assertTrue(result.hasErrors());

    }

    @Test
    public void testIsValidWithoutUserAccountService() {

        // Mock UserAccountValidator instance
        try (MockedConstruction<UserAccountValidator> mock = Mockito.mockConstruction(UserAccountValidator.class, (userAccountValidator, context) -> {

            // Mock isValid
            Mockito.when(userAccountValidator.isValid(Mockito.any(), Mockito.any())).then((args) -> {

                // Remove userAccountService
                ReflectionTestUtils.setField(userAccountValidator, "userAccountService", null);

                // Call real method
                return args.callRealMethod();

            });

        })) {

            // Create userAccount
            TestUserAccount userAccount = TestUserAccount.builder()
                    .username(UUID.randomUUID().toString())
                    .password(UUID.randomUUID().toString())
                    .build();

            // Mock findByUsername
            Mockito.when(userAccountService.findByUsername(userAccount.getUsername())).thenReturn(Optional.empty());

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(userAccount, "userAccount");

            // Validate userAccount
            this.validator.validate(userAccount, result);

            // Validate result
            Assertions.assertFalse(result.hasErrors());

        }

    }

}
