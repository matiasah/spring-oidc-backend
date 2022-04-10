package oidc.management.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Predicate;
import oidc.management.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import oidc.management.model.Authority;

/**
 * Authorities Controller
 * 
 * @author Matias Hermosilla
 * @since 03-02-2022
 * @see Authority
 * @see AuthorityService
 * @see PasswordEncoder
 * @see ObjectMapper
 */
@RestController
@RequestMapping("/authorities")
public class AuthorityController {
    
    @Autowired
    private AuthorityService authorityService;
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SpringValidatorAdapter validator;

    /**
     * Get all authorities.
     * 
     * @return List of authorities.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_authority')")
    @GetMapping
    public List<Authority> index() {
        // Return list of authorities
        return authorityService.findAll();
    }

    /**
     * Get all authorities.
     * 
     * @return Page of authorities.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_authority')")
    @GetMapping("page")
    public Page<Authority> page(Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        return this.authorityService.findAll(pageable, search);
    }

    /**
     * Get an authority by id.
     * 
     * @param id Authority id.
     * @return Authority.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_read_authority')")
    @GetMapping("{id}")
    public ResponseEntity<Authority> get(@PathVariable("id") String id) {
        // Get the optional holder
        Optional<Authority> optObject = this.authorityService.findById(id);

        // Verify if the holder contains a value
        if (optObject.isPresent()) {
            // Get the authority
            Authority object = optObject.get();

            // Return the authority
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Creates a new authority.
     * 
     * @param object The authority to create.
     * @return The created authority.
     * @throws BindException If the object is not valid.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_create_authority')")
    @PostMapping
    public ResponseEntity<Authority> save(@RequestBody Authority object) throws BindException {
        // Remove the id
        object.setId(null);

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(object, "authority");

        // Validate authority
        this.validator.validate(object, result);

        // If there are errors
        if (result.hasErrors()) {
            // Throw exception
            throw new BindException(result);
        }

        // Save the authority
        this.authorityService.save(object);

        // Return the authority
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    /**
     * Updates an authority.
     * 
     * @param id The authority id.
     * @param request The request.
     * @return The updated authority.
     * @throws IOException If the request body cannot be parsed.
     * @throws BindException If the object is not valid.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_update_authority')")
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authority> update(@PathVariable("id") String id, HttpServletRequest request) throws IOException, BindException {
            
        // Get the optional holder
        Optional<Authority> optional = this.authorityService.findById(id);

        // Verify if the holder contains a value
        if ( optional.isPresent() ) {

            // Get the object
            Authority object = optional.get();

            // Update the object
            object = mapper.readerForUpdating(object).readValue(request.getReader());

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(object, "object");

            // Validate object
            this.validator.validate(object, result);

            // If there are errors
            if (result.hasErrors()) {
                // Throw exception
                throw new BindException(result);
            }

            // Save the object
            this.authorityService.save(object);

            // Return the object
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return bad request
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Deletes an authority.
     * 
     * @param id The authority id.
     * @return The deleted authority.
     */
    @Secured({"ROLE_OIDC_ADMIN"})
    @PreAuthorize("hasAuthority('SCOPE_delete_authority')")
    @DeleteMapping("{id}")
    public ResponseEntity<Authority> delete(@PathVariable("id") String id) {

        // Delete the authority by it's id
        this.authorityService.deleteById(id);

        // Return ok
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
