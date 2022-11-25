package oidc.management.validation.validator;

import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountService;
import oidc.management.validation.annotations.ValidServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Default {@link ServiceAccount} validator.
 * It validates that two {@link ServiceAccount}s don't have the same clientId.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
public class ServiceAccountValidator implements ConstraintValidator<ValidServiceAccount, ServiceAccount> {

    @Autowired
    private ServiceAccountService serviceAccountService;

    @Transactional
    @Override
    public boolean isValid(ServiceAccount serviceAccount, ConstraintValidatorContext constraintValidatorContext) {

        // If service account service is not present
        if (serviceAccountService == null) {

            // Skip validation
            return true;

        }

        // Find a serviceAccount with the same clientId.
        Optional<ServiceAccount> optServiceAccountWithSameClientId = serviceAccountService
                .findByClientId(serviceAccount.getClientId());

        // If the serviceAccount with the same clientId is found
        if (optServiceAccountWithSameClientId.isPresent()) {

            // Get service account with same client id
            ServiceAccount serviceAccountWithSameClientId = optServiceAccountWithSameClientId.get();

            // If ids don't match
            if (serviceAccount.getId() == null || !serviceAccountWithSameClientId.getId().equals(serviceAccount.getId())) {

                // The serviceAccount is not valid
                return false;

            }

        }

        // The serviceAccount is valid
        return true;

    }

}
