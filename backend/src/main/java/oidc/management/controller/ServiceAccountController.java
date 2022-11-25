package oidc.management.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import oidc.management.model.ServiceAccount;

/**
 * Service Account Controller
 * 
 * @author Matias Hermosilla
 * @since 03-02-2022
 * @see ServiceAccount
 * @see ServiceAccountService
 * @see PasswordEncoder
 * @see ObjectMapper
 */
@RestController
@RequestMapping("/service-accounts")
public class ServiceAccountController {
    
    @Autowired
    private ServiceAccountService serviceAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SpringValidatorAdapter validator;

    /**
     * Get all service accounts.
     * 
     * @return List of service accounts.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_service_account')")
    @GetMapping
    public List<ServiceAccount> index() {
        // Return list of service accounts
        return serviceAccountService.findAll();
    }

    /**
     * Get all service accounts.
     * 
     * @return Page of service accounts.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_service_account')")
    @GetMapping("page")
    public Page<ServiceAccount> page(Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        return this.serviceAccountService.findAll(pageable, search);
    }

    /**
     * Get a service account by id.
     * 
     * @param id Service account id.
     * @return Service account.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_service_account')")
    @GetMapping("{id}")
    public ResponseEntity<ServiceAccount> get(@PathVariable("id") String id) {
        // Get the optional holder
        Optional<ServiceAccount> optObject = this.serviceAccountService.findById(id);

        // Verify if the holder contains a value
        if (optObject.isPresent()) {
            // Get the service account
            ServiceAccount object = optObject.get();

            // Return the service account
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Creates a new service account.
     * 
     * @param object The service account to create.
     * @return The created service account.
     * @throws BindException If the service account is not valid.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_create_service_account')")
    @PostMapping
    public ResponseEntity<ServiceAccount> save(@RequestBody ServiceAccount object) throws BindException {
        // Remove the id
        object.setId(null);

        // If the password is not empty
        if (object.getClientSecret() != null) {

            // Encode the password
            object.setClientSecret(this.passwordEncoder.encode(object.getClientSecret()));
        }

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(object, "serviceAccount");

        // Validate authority
        this.validator.validate(object, result);

        // If there are errors
        if (result.hasErrors()) {
            // Throw exception
            throw new BindException(result);
        }

        // Save the service account
        this.serviceAccountService.save(object);

        // Return the service account
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    /**
     * Updates a service account.
     * 
     * @param id The service account id.
     * @param request The request.
     * @return The updated service account.
     * @throws IOException If the request body cannot be parsed.
     * @throws BindException If the service account is not valid.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_update_service_account')")
    @PatchMapping("{id}")
    public ResponseEntity<ServiceAccount> update(@PathVariable("id") String id, HttpServletRequest request) throws IOException, BindException {
            
        // Get the optional holder
        Optional<ServiceAccount> optional = this.serviceAccountService.findById(id);

        // Verify if the holder contains a value
        if ( optional.isPresent() ) {

            // Get the service account
            ServiceAccount object = optional.get();
            
            // Get the previous password
            String password = object.getClientSecret();

            // Update the service account
            object = mapper.readerForUpdating(object).readValue(request.getReader());
            
            // If the previous password does not match the new password
            if ( object.getClientSecret() != null && !password.equals(object.getClientSecret()) ) {

                // Encode the password
                object.setClientSecret(this.passwordEncoder.encode(object.getClientSecret()));
            }

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(object, "serviceAccount");

            // Validate authority
            this.validator.validate(object, result);

            // If there are errors
            if (result.hasErrors()) {
                // Throw exception
                throw new BindException(result);
            }

            // Save the service account
            this.serviceAccountService.save(object);

            // Return the service account
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return bad request
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Deletes a service account.
     * 
     * @param id The service account id.
     * @return The deleted service account.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_delete_service_account')")
    @DeleteMapping("{id}")
    public ResponseEntity<ServiceAccount> delete(@PathVariable("id") String id) {

        // Delete the service account by it's id
        this.serviceAccountService.deleteById(id);

        // Return ok
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
