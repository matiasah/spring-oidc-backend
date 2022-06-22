package oidc.management.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        UserConsentController.class
})
public class UserConsentControllerTests {

    @Autowired
    private UserConsentController userConsentController;

    @Test
    public void testConsent() {
        // Test consent
        Assertions.assertNotNull(userConsentController.consent());
    }

}
