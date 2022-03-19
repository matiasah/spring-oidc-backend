package oidc.management.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;

/**
 * Service Account Controller
 * 
 * @author Matias Hermosilla
 * @since 03-02-2022
 * @see ServiceAccount
 * @see ServiceAccountRepository
 * @see PasswordEncoder
 * @see ObjectMapper
 */
@RestController
@RequestMapping("/service-accounts")
public class ServiceAccountController {
    
    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

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
    @GetMapping
    public List<ServiceAccount> index() {
        // Return list of service accounts
        return serviceAccountRepository.findAll();
    }

    /**
     * Get all service accounts.
     * 
     * @return Page of service accounts.
     */
    @GetMapping("page")
    public Page<ServiceAccount> page(@QuerydslPredicate(root = ServiceAccount.class) Predicate predicate, Pageable pageable) {
        if (predicate != null) {
            return this.serviceAccountRepository.findAll(predicate, pageable);
        }
        return this.serviceAccountRepository.findAll(pageable);
    }

    /**
     * Get a service account by id.
     * 
     * @param id Service account id.
     * @return Service account.
     */    
    @GetMapping("{id}")
    public ResponseEntity<ServiceAccount> get(@PathVariable("id") String id) {
        // Get the optional holder
        Optional<ServiceAccount> optObject = this.serviceAccountRepository.findById(id);

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
        this.serviceAccountRepository.save(object);

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
    @PatchMapping("{id}")
    public ResponseEntity<ServiceAccount> update(@PathVariable("id") String id, HttpServletRequest request) throws IOException, BindException {
            
        // Get the optional holder
        Optional<ServiceAccount> optional = this.serviceAccountRepository.findById(id);

        // Verify if the holder contains a value
        if ( optional.isPresent() ) {

            // Get the service account
            ServiceAccount object = optional.get();
            
            // Get the previous password
            String password = object.getClientSecret();

            // Update the service account
            object = mapper.readerForUpdating(object).readValue(request.getReader());
            
            // If the previous password does not match the new password
            if ( !password.equals(object.getClientSecret()) && object.getClientSecret() != null ) {

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
            this.serviceAccountRepository.save(object);

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
    @DeleteMapping("{id}")
    public ResponseEntity<ServiceAccount> delete(@PathVariable("id") String id) {

        // Delete the service account by it's id
        this.serviceAccountRepository.deleteById(id);

        // Return ok
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
