package oidc.management.config;

import oidc.management.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {
        ServiceConfig.class
})
public class ServiceConfigTests {

    @MockBean
    private ServiceAccountRepository serviceAccountRepository;

    @MockBean
    private UserAccountRepository userAccountRepository;

    @MockBean
    private ScopeRepository scopeRepository;

    @MockBean
    private AuthorityRepository authorityRepository;

    @MockBean
    private AuditEventRepository auditEventRepository;

    @Test
    public void testContext() {

    }

}
