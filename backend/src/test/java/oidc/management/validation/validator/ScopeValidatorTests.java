package oidc.management.validation.validator;

import oidc.management.config.ValidationConfig;
import oidc.management.service.ScopeService;
import oidc.management.test.TestScope;
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
public class ScopeValidatorTests {

    @Autowired
    private SpringValidatorAdapter validator;

    @MockBean
    private ScopeService scopeService;

    @Test
    public void testIsValid() {

        // Create scope
        TestScope scope = TestScope.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(scopeService.findByName(scope.getName())).thenReturn(Optional.empty());

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(scope, "scope");

        // Validate scope
        this.validator.validate(scope, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameName() {

        // Create scope
        TestScope scope = TestScope.builder()
                .id(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(scopeService.findByName(scope.getName())).thenReturn(Optional.of(scope));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(scope, "scope");

        // Validate scope
        this.validator.validate(scope, result);

        // Validate result
        Assertions.assertFalse(result.hasErrors());

    }

    @Test
    public void testIsValidWithSameNameAndDifferentId() {

        // Create scope
        TestScope scope = TestScope.builder()
                .id(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();

        // Create scope
        TestScope responseScope = TestScope.builder()
                .id(UUID.randomUUID().toString())
                .name(scope.getName())
                .description(UUID.randomUUID().toString())
                .build();

        // Mock findByName
        Mockito.when(scopeService.findByName(scope.getName())).thenReturn(Optional.of(responseScope));

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(scope, "scope");

        // Validate scope
        this.validator.validate(scope, result);

        // Validate result
        Assertions.assertTrue(result.hasErrors());

    }

    @Test
    public void testIsValidWithoutScopeService() {

        // Mock ScopeValidator instance
        try (MockedConstruction<ScopeValidator> mock = Mockito.mockConstruction(ScopeValidator.class, (scopeValidator, context) -> {

            // Mock isValid
            Mockito.when(scopeValidator.isValid(Mockito.any(), Mockito.any())).then((args) -> {

                // Remove scopeService
                ReflectionTestUtils.setField(scopeValidator, "scopeService", null);

                // Call real method
                return args.callRealMethod();

            });

        })) {

            // Create scope
            TestScope scope = TestScope.builder()
                    .name(UUID.randomUUID().toString())
                    .description(UUID.randomUUID().toString())
                    .build();

            // Mock findByName
            Mockito.when(scopeService.findByName(scope.getName())).thenReturn(Optional.empty());

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(scope, "scope");

            // Validate scope
            this.validator.validate(scope, result);

            // Validate result
            Assertions.assertFalse(result.hasErrors());

        }

    }

}
