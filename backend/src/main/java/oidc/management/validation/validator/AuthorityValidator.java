package oidc.management.validation.validator;

import oidc.management.model.Authority;
import oidc.management.service.AuthorityService;
import oidc.management.validation.annotations.ValidAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Default {@link Authority} validator.
 * It validates that two {@link Authority}s don't have the same name.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
public class AuthorityValidator implements ConstraintValidator<ValidAuthority, Authority> {

    @Autowired
    private AuthorityService authorityService;

    @Transactional
    @Override
    public boolean isValid(Authority authority, ConstraintValidatorContext constraintValidatorContext) {

        // If authority service is not present
        if (authorityService == null) {

            // Skip validation
            return true;
        }

        // If the authority has an id, it means that it already persisted.
        if (authority.getId() != null) {

            // Find an authority with the same name.
            Optional<Authority> authorityWithSameName = authorityService
                    .findByName(authority.getName());

            // If the authority with the same name is found
            if (authorityWithSameName.isPresent()) {

                // If the authority with the same name is the same as the one that is being validated,
                if (authorityWithSameName.get().getId().equals(authority.getId())) {

                    // The authority is valid.
                    return true;
                }

                // The authority is not valid.
                return false;

            }

            // The authority is valid.
            return true;
        }

        // Find an authority with the same name.
        Optional<Authority> authorityWithSameName = authorityService
                .findByName(authority.getName());

        // If the authority with the same name is found
        if (authorityWithSameName.isPresent()) {

            // The authority is not valid.
            return false;
        }

        // The authority is valid.
        return true;

    }

}
