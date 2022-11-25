package oidc.management.validation.validator;

import oidc.management.model.Scope;
import oidc.management.service.ScopeService;
import oidc.management.validation.annotations.ValidScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Default {@link Scope} validator.
 * It validates that two {@link Scope}s don't have the same name.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
public class ScopeValidator implements ConstraintValidator<ValidScope, Scope> {

    @Autowired
    private ScopeService scopeService;

    @Transactional
    @Override
    public boolean isValid(Scope scope, ConstraintValidatorContext constraintValidatorContext) {

        // If scope service is not present
        if (scopeService == null) {

            // Skip validation
            return true;

        }

        // Find a scope with the same name.
        Optional<Scope> optScopeWithSameName = scopeService.findByName(scope.getName());

        // If the scope with the same name is found
        if (optScopeWithSameName.isPresent()) {

            // Get scope with same name
            Scope scopeWithSameName = optScopeWithSameName.get();

            // If ids don't match
            if (scope.getId() == null || !scopeWithSameName.getId().equals(scope.getId())) {

                // The scope is not valid.
                return false;

            }

        }

        // The scope is valid.
        return true;

    }

}
