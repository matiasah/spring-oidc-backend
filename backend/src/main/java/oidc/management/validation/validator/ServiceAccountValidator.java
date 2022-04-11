package oidc.management.validation.validator;

import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountService;
import oidc.management.validation.annotations.ValidServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
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

    @Override
    public boolean isValid(ServiceAccount serviceAccount, ConstraintValidatorContext constraintValidatorContext) {
        // If the serviceAccount has an id, it means it is already persisted.
        if (serviceAccount.getId() != null) {

            // Find a serviceAccount with the same clientId.
            Optional<ServiceAccount> serviceAccountWithSameClientId = serviceAccountService
                    .findByClientId(serviceAccount.getClientId());

            // If the serviceAccount with the same clientId is found
            if (serviceAccountWithSameClientId.isPresent()) {

                // If the serviceAccount with the same clientId is the same as the one being validated
                if (serviceAccountWithSameClientId.get().getId().equals(serviceAccount.getId())) {

                    // The serviceAccount is valid
                    return true;
                }

            }

            // The serviceAccount is not valid
            return false;
        }

        // Find a serviceAccount with the same clientId.
        Optional<ServiceAccount> serviceAccountWithSameClientId = serviceAccountService
                .findByClientId(serviceAccount.getClientId());

        // If the serviceAccount with the same clientId is found
        if (serviceAccountWithSameClientId.isPresent()) {

            // The serviceAccount is not valid
            return false;
        }

        // The serviceAccount is valid
        return true;
    }

}
