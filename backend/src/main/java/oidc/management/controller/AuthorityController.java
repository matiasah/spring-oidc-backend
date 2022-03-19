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
import oidc.management.model.Authority;
import oidc.management.repository.AuthorityRepository;

/**
 * Authorities Controller
 * 
 * @author Matias Hermosilla
 * @since 03-02-2022
 * @see Authority
 * @see AuthorityRepository
 * @see PasswordEncoder
 * @see ObjectMapper
 */
@RestController
@RequestMapping("/authorities")
public class AuthorityController {
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SpringValidatorAdapter validator;

    /**
     * Get all authorities.
     * 
     * @return List of authorities.
     */
    @GetMapping
    public List<Authority> index() {
        // Return list of authorities
        return authorityRepository.findAll();
    }

    /**
     * Get all authorities.
     * 
     * @return Page of authorities.
     */
    @GetMapping("page")
    public Page<Authority> page(@QuerydslPredicate(root = Authority.class) Predicate predicate, Pageable pageable) {
        if (predicate != null) {
            return this.authorityRepository.findAll(predicate, pageable);
        }
        return this.authorityRepository.findAll(pageable);
    }

    /**
     * Get an authority by id.
     * 
     * @param id Authority id.
     * @return Authority.
     */    
    @GetMapping("{id}")
    public ResponseEntity<Authority> get(@PathVariable("id") String id) {
        // Get the optional holder
        Optional<Authority> optObject = this.authorityRepository.findById(id);

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
        this.authorityRepository.save(object);

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
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authority> update(@PathVariable("id") String id, HttpServletRequest request) throws IOException, BindException {
            
        // Get the optional holder
        Optional<Authority> optional = this.authorityRepository.findById(id);

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
            this.authorityRepository.save(object);

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
    @DeleteMapping("{id}")
    public ResponseEntity<Authority> delete(@PathVariable("id") String id) {

        // Delete the authority by it's id
        this.authorityRepository.deleteById(id);

        // Return ok
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
