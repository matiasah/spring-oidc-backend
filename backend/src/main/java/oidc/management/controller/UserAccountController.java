package oidc.management.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import oidc.management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import oidc.management.model.UserAccount;

/**
 * User Account Controller
 * 
 * @author Matias Hermosilla
 * @since 03-02-2022
 * @see UserAccount
 * @see UserAccountService
 * @see PasswordEncoder
 * @see ObjectMapper
 */
@RestController
@RequestMapping("/user-accounts")
public class UserAccountController {
    
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SpringValidatorAdapter validator;

    /**
     * Get all user accounts.
     * 
     * @return List of user accounts.
     */
    @GetMapping
    public List<UserAccount> index() {
        // Return list of user accounts
        return userAccountService.findAll();
    }

    /**
     * Get all user accounts.
     * 
     * @return Page of user accounts.
     */
    @GetMapping("page")
    public Page<UserAccount> page(Pageable pageable) {
        return this.userAccountService.findAll(pageable);
    }

    /**
     * Get a user account by id.
     * 
     * @param id User account id.
     * @return User account.
     */    
    @GetMapping("{id}")
    public ResponseEntity<UserAccount> get(@PathVariable("id") String id) {
        // Get the optional holder
        Optional<UserAccount> optObject = this.userAccountService.findById(id);

        // Verify if the holder contains a value
        if (optObject.isPresent()) {
            // Get the user account
            UserAccount object = optObject.get();

            // Return the user account
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Creates a new user account.
     * 
     * @param object The user account to create.
     * @return The created user account.
     * @throws BindException If the user account is not valid.
     */
    @PostMapping
    public ResponseEntity<UserAccount> save(@RequestBody UserAccount object) throws BindException {
        // Remove the id
        object.setId(null);

        // If the password is not empty
        if (object.getPassword() != null) {

            // Encode the password
            object.setPassword(this.passwordEncoder.encode(object.getPassword()));
        }

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(object, "userAccount");

        // Validate authority
        this.validator.validate(object, result);

        // If there are errors
        if (result.hasErrors()) {
            // Throw exception
            throw new BindException(result);
        }

        // Save the user account
        this.userAccountService.save(object);

        // Return the user account
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    /**
     * Updates a user account.
     * 
     * @param id The user account id.
     * @param request The request.
     * @return The updated user account.
     * @throws IOException If the request body cannot be parsed.
     * @throws BindException If the user account is not valid.
     */
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccount> update(@PathVariable("id") String id, HttpServletRequest request) throws IOException, BindException {
            
        // Get the optional holder
        Optional<UserAccount> optional = this.userAccountService.findById(id);

        // Verify if the holder contains a value
        if ( optional.isPresent() ) {

            // Get the user account
            UserAccount object = optional.get();
            
            // Get the previous password
            String password = object.getPassword();

            // Update the user account
            object = mapper.readerForUpdating(object).readValue(request.getReader());
            
            // If the previous password does not match the new password
            if ( !password.equals(object.getPassword()) && object.getPassword() != null ) {

                // Encode the password
                object.setPassword(this.passwordEncoder.encode(object.getPassword()));
            }

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(object, "userAccount");

            // Validate authority
            this.validator.validate(object, result);

            // If there are errors
            if (result.hasErrors()) {
                // Throw exception
                throw new BindException(result);
            }

            // Save the user account
            this.userAccountService.save(object);

            // Return the user account
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return bad request
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Deletes a user account.
     * 
     * @param id The user account id.
     * @return The deleted user account.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<UserAccount> delete(@PathVariable("id") String id) {

        // Delete the user account by it's id
        this.userAccountService.deleteById(id);

        // Return ok
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
