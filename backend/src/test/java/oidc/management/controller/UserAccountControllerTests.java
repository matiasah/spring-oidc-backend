package oidc.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oidc.management.model.UserAccount;
import oidc.management.service.UserAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest(classes = {
        UserAccountController.class,
        ObjectMapper.class
})
public class UserAccountControllerTests {

    @MockBean
    private UserAccountService userAccountService;

    @MockBean
    private SpringValidatorAdapter springValidatorAdapter;

    @Autowired
    private UserAccountController userAccountController;

    private List<UserAccount> all = new ArrayList<>();

    @Mock
    private Pageable pageable;

    @Mock
    private Page page;

    @Mock
    private UserAccount userAccount;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testIndex() {
        // Mock findAll() method
        Mockito.when(userAccountService.findAll()).thenReturn(all);

        // Test
        Assertions.assertEquals(all, userAccountController.index());
    }

    @Test
    public void testPage() {
        // Mock findAll
        Mockito.when(userAccountService.findAll(Mockito.eq(pageable), Mockito.anyString())).thenReturn(page);

        // Test
        Assertions.assertEquals(page, userAccountController.page(pageable, ""));
    }

    @Test
    public void testGet() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<UserAccount> optional = Optional.of(userAccount);

        // Mock findById
        Mockito.when(userAccountService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.get(id);

        // Validate body
        Assertions.assertEquals(userAccount, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetNoContent() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<UserAccount> optional = Optional.empty();

        // Mock findById
        Mockito.when(userAccountService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.get(id);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testSave() throws BindException {
        // Mock secret
        Mockito.when(userAccount.getPassword()).thenReturn("");

        // Mock save
        Mockito.when(userAccountService.save(userAccount)).thenReturn(userAccount);

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.save(userAccount);

        // Validate body
        Assertions.assertEquals(userAccount, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testSaveNoSecret() throws BindException {
        // Mock save
        Mockito.when(userAccountService.save(userAccount)).thenReturn(userAccount);

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.save(userAccount);

        // Validate body
        Assertions.assertEquals(userAccount, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testSaveBindException() throws BindException {

        // Mock save
        Mockito.when(userAccountService.save(userAccount)).thenReturn(userAccount);

        Mockito.doAnswer((args) -> {
            // Get BindingResult
            BindingResult bindingResult = args.getArgument(1);

            // Add error
            bindingResult.addError(new ObjectError(bindingResult.getObjectName(), "test"));

            // Return null
            return null;
        }).when(springValidatorAdapter).validate(Mockito.eq(userAccount), Mockito.any(BindingResult.class));

        // Test method
        Assertions.assertThrows(BindException.class, () -> userAccountController.save(userAccount));

    }

    @Test
    public void testUpdate() throws BindException, IOException {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<UserAccount> optional = Optional.of(userAccount);

        // Mock findById
        Mockito.when(userAccountService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Mock getClientSecret
        Mockito.when(userAccount.getPassword()).thenReturn("");

        // Mock save
        Mockito.when(userAccountService.save(userAccount)).thenReturn(userAccount);

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.update(id, request);

        // Validate body
        Assertions.assertEquals(userAccount, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testUpdateSecretChange() throws BindException, IOException {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<UserAccount> optional = Optional.of(userAccount);

        // Mock findById
        Mockito.when(userAccountService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{\"password\": \"test\"}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        AtomicReference<String> secret = new AtomicReference<>("");

        // Mock getClientSecret
        Mockito.doAnswer((args) -> {
            // Return secret
            return secret.get();
        }).when(userAccount).getPassword();

        // Mock setClientSecret
        Mockito.doAnswer((args) -> {
            // Set secret
            secret.set(args.getArgument(0));

            // Return null
            return null;
        }).when(userAccount).setPassword(Mockito.anyString());

        // Mock save
        Mockito.when(userAccountService.save(userAccount)).thenReturn(userAccount);

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.update(id, request);

        // Validate body
        Assertions.assertEquals(userAccount, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testUpdateBindException() {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<UserAccount> optional = Optional.of(userAccount);

        // Mock findById
        Mockito.when(userAccountService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Mock getClientSecret
        Mockito.when(userAccount.getPassword()).thenReturn("");

        Mockito.doAnswer((args) -> {
            // Get BindingResult
            BindingResult bindingResult = args.getArgument(1);

            // Add error
            bindingResult.addError(new ObjectError(bindingResult.getObjectName(), "test"));

            // Return null
            return null;
        }).when(springValidatorAdapter).validate(Mockito.eq(userAccount), Mockito.any(BindingResult.class));

        // Test method
        Assertions.assertThrows(BindException.class, () -> userAccountController.update(id, request));

    }

    @Test
    public void testUpdateBadRequest() throws BindException, IOException {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<UserAccount> optional = Optional.empty();

        // Mock findById
        Mockito.when(userAccountService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.update(id, request);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void testDelete() {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Get response
        ResponseEntity<UserAccount> responseEntity = userAccountController.delete(id);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}
