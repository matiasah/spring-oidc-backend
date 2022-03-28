package oidc.management.service.impl;

import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import oidc.management.service.ServiceAccountEncryptionService;
import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link ServiceAccountService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
@Service
@ConditionalOnMissingBean(ServiceAccountService.class)
public class DefaultServiceAccountService implements ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    @Autowired
    private ServiceAccountEncryptionService serviceAccountEncryptionService;

    @Override
    public List<ServiceAccount> findAll() {
        // Find all Service Accounts
        return serviceAccountRepository.findAll()
                .stream()
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt(serviceAccount)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Page<ServiceAccount> findAll(Pageable pageable) {
        // Find all Service Accounts
        return serviceAccountRepository.findAll(pageable)
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt(serviceAccount)
                );
    }

    @Override
    public Optional<ServiceAccount> findById(String id) {
        // Find Service Account by id
        return serviceAccountRepository.findById(id)
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt(serviceAccount)
                );
    }

    @Override
    public Optional<ServiceAccount> findByClientId(String clientId) {
        // Find Service Account by clientId
        return serviceAccountRepository.findByClientId(clientId)
                .map(
                        // Decrypt Service Account
                        serviceAccount -> serviceAccountEncryptionService.decrypt(serviceAccount)
                );
    }

    @Override
    public ServiceAccount save(ServiceAccount serviceAccount) {
        // Encrypt the Service Account before saving it
        return serviceAccountRepository.save(
                serviceAccountEncryptionService.encrypt(serviceAccount)
        );
    }

    @Override
    public void deleteById(String id) {
        // Delete the Service Account by id
        serviceAccountRepository.deleteById(id);
    }

}
