package oidc.management.service.impl;

import oidc.management.model.Scope;
import oidc.management.service.ScopeEncryptionService;

/**
 * Default implementation of {@link ScopeEncryptionService}.
 *
 * @author Matías Hermosilla
 * @since 09-04-2022
 */
public class DefaultScopeEncryptionService implements ScopeEncryptionService {

    @Override
    public Scope encrypt(Scope scope) {
        return scope;
    }

    @Override
    public Scope decrypt(Scope scope) {
        return scope;
    }

}
