package oidc.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oidc.management.model.Authority;
import oidc.management.service.AuthorityService;
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
        AuthorityController.class,
        ObjectMapper.class
})
public class AuthorityControllerTests {

    @MockBean
    private AuthorityService authorityService;

    @MockBean
    private SpringValidatorAdapter springValidatorAdapter;

    @Autowired
    private AuthorityController authorityController;

    private List<Authority> all = new ArrayList<>();

    @Mock
    private Pageable pageable;

    @Mock
    private Page page;

    @Mock
    private Authority authority;

    @Test
    public void testIndex() {
        // Mock findAll() method
        Mockito.when(authorityService.findAll()).thenReturn(all);

        // Test
        Assertions.assertEquals(all, authorityController.index());
    }

    @Test
    public void testPage() {
        // Mock findAll
        Mockito.when(authorityService.findAll(Mockito.eq(pageable), Mockito.anyString())).thenReturn(page);

        // Test
        Assertions.assertEquals(page, authorityController.page(pageable, ""));
    }

    @Test
    public void testGet() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Authority> optional = Optional.of(authority);

        // Mock findById
        Mockito.when(authorityService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<Authority> responseEntity = authorityController.get(id);

        // Validate body
        Assertions.assertEquals(authority, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetNoContent() {
        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Authority> optional = Optional.empty();

        // Mock findById
        Mockito.when(authorityService.findById(id)).thenReturn(optional);

        // Get response
        ResponseEntity<Authority> responseEntity = authorityController.get(id);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testSave() throws BindException {
        // Mock save
        Mockito.when(authorityService.save(authority)).thenReturn(authority);

        // Get response
        ResponseEntity<Authority> responseEntity = authorityController.save(authority);

        // Validate body
        Assertions.assertEquals(authority, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testSaveBindException() throws BindException {

        // Mock save
        Mockito.when(authorityService.save(authority)).thenReturn(authority);

        Mockito.doAnswer((args) -> {
            // Get BindingResult
            BindingResult bindingResult = args.getArgument(1);

            // Add error
            bindingResult.addError(new ObjectError(bindingResult.getObjectName(), "test"));

            // Return null
            return null;
        }).when(springValidatorAdapter).validate(Mockito.eq(authority), Mockito.any(BindingResult.class));

        // Test method
        Assertions.assertThrows(BindException.class, () -> authorityController.save(authority));

    }

    @Test
    public void testUpdate() throws BindException, IOException {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Authority> optional = Optional.of(authority);

        // Mock findById
        Mockito.when(authorityService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Mock save
        Mockito.when(authorityService.save(authority)).thenReturn(authority);

        // Get response
        ResponseEntity<Authority> responseEntity = authorityController.update(id, request);

        // Validate body
        Assertions.assertEquals(authority, responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testUpdateBindException() {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Authority> optional = Optional.of(authority);

        // Mock findById
        Mockito.when(authorityService.findById(id)).thenReturn(optional);

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
        }).when(springValidatorAdapter).validate(Mockito.eq(authority), Mockito.any(BindingResult.class));

        // Test method
        Assertions.assertThrows(BindException.class, () -> authorityController.update(id, request));

    }

    @Test
    public void testUpdateBadRequest() throws BindException, IOException {

        // Define ID
        final String id = UUID.randomUUID().toString();

        // Create optional
        Optional<Authority> optional = Optional.empty();

        // Mock findById
        Mockito.when(authorityService.findById(id)).thenReturn(optional);

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Get response
        ResponseEntity<Authority> responseEntity = authorityController.update(id, request);

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
        ResponseEntity<Authority> responseEntity = authorityController.delete(id);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}
