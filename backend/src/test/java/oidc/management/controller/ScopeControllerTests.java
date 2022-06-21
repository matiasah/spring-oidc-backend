package oidc.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oidc.management.model.Scope;
import oidc.management.service.ScopeService;
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

@SpringBootTest(classes = {
        ScopeController.class,
        ObjectMapper.class
})
public class ScopeControllerTests {

    @MockBean
    private ScopeService scopeService;

    @MockBean
    private SpringValidatorAdapter springValidatorAdapter;

    @Autowired
    private ScopeController scopeController;

    private List<Scope> all = new ArrayList<>();

    @Mock
    private Pageable pageable;

    @Mock
    private Page page;

    @Mock
    private Scope scope;

    @Test
    public void testIndex() {
        // Mock findAll() method
        Mockito.when(scopeService.findAll()).thenReturn(all);

        // Test
        Assertions.assertEquals(all, scopeController.index());
    }

    @Test
    public void testPage() {
        // Mock findAll
        Mockito.when(scopeService.findAll(Mockito.eq(pageable), Mockito.anyString())).thenReturn(page);

        // Test
        Assertions.assertEquals(page, scopeController.page(pageable, ""));
    }

    @Test
    public void testGet() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Scope> optional = Optional.of(scope);

        // Mock findById
        Mockito.when(scopeService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<Scope> responseEntity = scopeController.get(id);

        // Validate body
        Assertions.assertEquals(scope, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetNoContent() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Scope> optional = Optional.empty();

        // Mock findById
        Mockito.when(scopeService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<Scope> responseEntity = scopeController.get(id);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testSave() throws BindException {
        // Mock save
        Mockito.when(scopeService.save(scope)).thenReturn(scope);

        // Get response
        ResponseEntity<Scope> responseEntity = scopeController.save(scope);

        // Validate body
        Assertions.assertEquals(scope, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testSaveBindException() throws BindException {

        // Mock save
        Mockito.when(scopeService.save(scope)).thenReturn(scope);

        Mockito.doAnswer((args) -> {
            // Get BindingResult
            BindingResult bindingResult = args.getArgument(1);

            // Add error
            bindingResult.addError(new ObjectError(bindingResult.getObjectName(), "test"));

            // Return null
            return null;
        }).when(springValidatorAdapter).validate(Mockito.eq(scope), Mockito.any(BindingResult.class));

        // Test method
        Assertions.assertThrows(BindException.class, () -> scopeController.save(scope));

    }

    @Test
    public void testUpdate() throws BindException, IOException {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Scope> optional = Optional.of(scope);

        // Mock findById
        Mockito.when(scopeService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Mock save
        Mockito.when(scopeService.save(scope)).thenReturn(scope);

        // Get response
        ResponseEntity<Scope> responseEntity = scopeController.update(id, request);

        // Validate body
        Assertions.assertEquals(scope, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testUpdateBindException() {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Scope> optional = Optional.of(scope);

        // Mock findById
        Mockito.when(scopeService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Mockito.doAnswer((args) -> {
            // Get BindingResult
            BindingResult bindingResult = args.getArgument(1);

            // Add error
            bindingResult.addError(new ObjectError(bindingResult.getObjectName(), "test"));

            // Return null
            return null;
        }).when(springValidatorAdapter).validate(Mockito.eq(scope), Mockito.any(BindingResult.class));

        // Test method
        Assertions.assertThrows(BindException.class, () -> scopeController.update(id, request));

    }

    @Test
    public void testUpdateBadRequest() throws BindException, IOException {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Scope> optional = Optional.empty();

        // Mock findById
        Mockito.when(scopeService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Get response
        ResponseEntity<Scope> responseEntity = scopeController.update(id, request);

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
        ResponseEntity<Scope> responseEntity = scopeController.delete(id);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}
