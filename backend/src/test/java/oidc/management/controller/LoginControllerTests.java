package oidc.management.controller;

import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        LoginController.class
})
public class LoginControllerTests {

    @Autowired
    private LoginController loginController;

    @Test
    public void testLogin() {
        // Test login
        Assertions.assertNotNull(loginController.login());
    }

}
