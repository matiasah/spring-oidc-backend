package oidc.management.validation.validator;

import oidc.management.config.ValidationConfig;
import oidc.management.service.AuthorityService;
import oidc.management.test.TestAuthority;
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
public class AuthorityValidatorTests {

    @Autowired
    private SpringValidatorAdapter validator;

    @MockBean
    private AuthorityService authorityService;

    @Test
    public void testIsValid() {

        // Create Authority
        TestAuthority authority = TestAuthority.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(authorityService.findByName(authority.getName())).thenReturn(Optional.empty());

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(authority, "authority");

        // Validate authority
        this.validator.validate(authority, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameName() {

        // Create Authority
        TestAuthority authority = TestAuthority.builder()
                .id(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(authorityService.findByName(authority.getName())).thenReturn(Optional.of(authority));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(authority, "authority");

        // Validate authority
        this.validator.validate(authority, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameNameAndDifferentId() {

        // Create Authority
        TestAuthority authority = TestAuthority.builder()
                .id(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Create Authority
        TestAuthority responseAuthority = TestAuthority.builder()
                .id(UUID.randomUUID().toString())
                .name(authority.getName())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(authorityService.findByName(authority.getName())).thenReturn(Optional.of(responseAuthority));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(authority, "authority");

        // Validate authority
        this.validator.validate(authority, result);

        // Validate result
        Assertions.assertTrue(result.hasErrors());

    }

    @Test
    public void testIsValidWithoutAuthorityService() {

        // Mock AuthorityValidator instance
        try (MockedConstruction<AuthorityValidator> mock = Mockito.mockConstruction(AuthorityValidator.class, (authorityValidator, context) -> {

            // Mock isValid
            Mockito.when(authorityValidator.isValid(Mockito.any(), Mockito.any())).then((args) -> {

                // Remove authorityService
                ReflectionTestUtils.setField(authorityValidator, "authorityService", null);

                // Call real method
                return args.callRealMethod();

            });

        })) {

            // Create Authority
            TestAuthority authority = TestAuthority.builder()
                    .name(UUID.randomUUID().toString())
                    .description(UUID.randomUUID().toString())
                    .build();

            // Mock findByName
            Mockito.when(authorityService.findByName(authority.getName())).thenReturn(Optional.empty());

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(authority, "authority");

            // Validate authority
            this.validator.validate(authority, result);

            // Validate result
            Assertions.assertFalse(result.hasErrors());

        }

    }

}
