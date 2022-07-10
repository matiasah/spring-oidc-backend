package oidc.management.service.impl;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import oidc.management.service.ServiceAccountEncryptionService;
import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link ServiceAccountService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
@Log4j2
public class DefaultServiceAccountService implements ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    @Autowired
    private ServiceAccountEncryptionService serviceAccountEncryptionService;

    @Override
    public ServiceAccount.ServiceAccountBuilder entityBuilder() {
        return serviceAccountRepository.entityBuilder();
    }

    @Override
    public List<ServiceAccount> findAll() {
        // Find all Service Accounts
        return (List<ServiceAccount>) serviceAccountRepository.findAll()
                .stream()
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt((ServiceAccount) serviceAccount)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Page<ServiceAccount> findAll(Pageable pageable, String search) {

        // If there is no search term or the search term is empty
        if (search == null || search.isEmpty()) {

            // Return all Service Accounts
            return serviceAccountRepository.findAll(pageable)
                    .map(
                            // Decrypt Service Account
                            serviceAccount -> serviceAccountEncryptionService.decrypt((ServiceAccount) serviceAccount)
                    );
        }

        // Return all Service Accounts that match the search term
        return serviceAccountRepository.findByTagsContainingIgnoreCase(search, pageable)
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt((ServiceAccount) serviceAccount)
                );
    }

    @Override
    public Optional<ServiceAccount> findById(String id) {
        // Find Service Account by id
        return serviceAccountRepository.findById(id)
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt((ServiceAccount) serviceAccount)
                );
    }

    @Override
    public Optional<ServiceAccount> findByClientId(String clientId) {
        // Find Service Account by clientId
        return serviceAccountRepository
                .findByHashedClientId(this.serviceAccountEncryptionService.getHashedClientId(clientId))
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt((ServiceAccount) serviceAccount)
                );
    }

    @Override
    public ServiceAccount save(ServiceAccount serviceAccount) {
        // Encrypt Service Account
        ServiceAccount encryptedServiceAccount = serviceAccountEncryptionService.encrypt(serviceAccount);

        try {
            // Save Service Account
            this.serviceAccountRepository.save(encryptedServiceAccount);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems saving service account", e);
        }

        // Set id
        serviceAccount.setId(encryptedServiceAccount.getId());

        // Return Service Account
        return serviceAccount;
    }

    @Override
    public void deleteById(String id) {
        try {
            // Delete the Service Account by id
            serviceAccountRepository.deleteById(id);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems deleting service account", e);
        }
    }

}
