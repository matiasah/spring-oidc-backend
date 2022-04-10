package oidc.management.validation.validator;

import oidc.management.model.UserAccount;
import oidc.management.service.UserAccountService;
import oidc.management.validation.annotations.ValidUserAccount;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Default {@link UserAccount} validator.
 * It validates that two {@link UserAccount}s don't have the same username.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
public class UserAccountValidator implements ConstraintValidator<ValidUserAccount, UserAccount> {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public boolean isValid(UserAccount userAccount, ConstraintValidatorContext constraintValidatorContext) {
        // If user account has an id
        if (userAccount.getId() != null) {

            // Find a user account with the same name
            Optional<UserAccount> userAccountWithSameName = userAccountService
                    .findByUsername(userAccount.getUsername());

            // If user account with the same name exists
            if (userAccountWithSameName.isPresent()) {

                // If the user account with the same name is not the same as the user account
                if (!userAccountWithSameName.get().getId().equals(userAccount.getId())) {

                    // The user account is not valid
                    return false;
                }

            }

            // The user account is valid
            return true;
        }

        // Find a user account with the same name
        Optional<UserAccount> userAccountWithSameName = userAccountService
                .findByUsername(userAccount.getUsername());

        // If user account with the same name exists
        if (userAccountWithSameName.isPresent()) {

            // The user account is not valid
            return false;
        }

        // The user account is valid
        return true;
    }

}
