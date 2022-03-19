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
import oidc.management.model.Scope;
import oidc.management.repository.ScopeRepository;

/**
 * Scopes Controller
 * 
 * @author Matias Hermosilla
 * @since 03-02-2022
 * @see PasswordEncoder
 * @see ObjectMapper
 */
@RestController
@RequestMapping("/scopes")
public class ScopeController {
    
    @Autowired
    private ScopeRepository scopeRepository;
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SpringValidatorAdapter validator;

    /**
     * Get all scopes.
     * 
     * @return List of scopes.
     */
    @GetMapping
    public List<Scope> index() {
        // Return list of scopes
        return scopeRepository.findAll();
    }

    /**
     * Get all scopes.
     * 
     * @return Page of scopes.
     */
    @GetMapping("page")
    public Page<Scope> page(@QuerydslPredicate(root = Scope.class) Predicate predicate, Pageable pageable) {
        if (predicate != null) {
            return this.scopeRepository.findAll(predicate, pageable);
        }
        return this.scopeRepository.findAll(pageable);
    }

    /**
     * Get a scope by id.
     * 
     * @param id Scope id.
     * @return Scope.
     */    
    @GetMapping("{id}")
    public ResponseEntity<Scope> get(@PathVariable("id") String id) {
        // Get the optional holder
        Optional<Scope> optObject = this.scopeRepository.findById(id);

        // Verify if the holder contains a value
        if (optObject.isPresent()) {
            // Get the scope
            Scope object = optObject.get();

            // Return the scope
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return no content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Creates a new scope.
     * 
     * @param object The scope to create.
     * @return The created scope.
     * @throws BindException If the scope is not valid.
     */
    @PostMapping
    public ResponseEntity<Scope> save(@RequestBody Scope object) throws BindException {
        // Remove the id
        object.setId(null);

        // Create validator
        BindingResult result = new BeanPropertyBindingResult(object, "scope");

        // Validate authority
        this.validator.validate(object, result);

        // If there are errors
        if (result.hasErrors()) {
            // Throw exception
            throw new BindException(result);
        }

        // Save the scope
        this.scopeRepository.save(object);

        // Return the scope
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    /**
     * Updates a scope.
     * 
     * @param id The scope id.
     * @param request The request.
     * @return The updated scope.
     * @throws IOException If the request body cannot be parsed.
     * @throws BindException If the scope is not valid.
     * @throws BindException If the scope is not found.
     */
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Scope> update(@PathVariable("id") String id, HttpServletRequest request) throws IOException, BindException {
            
        // Get the optional holder
        Optional<Scope> optional = this.scopeRepository.findById(id);

        // Verify if the holder contains a value
        if ( optional.isPresent() ) {

            // Get the object
            Scope object = optional.get();

            // Update the object
            object = mapper.readerForUpdating(object).readValue(request.getReader());

            // Create validator
            BindingResult result = new BeanPropertyBindingResult(object, "scope");

            // Validate authority
            this.validator.validate(object, result);

            // If there are errors
            if (result.hasErrors()) {
                // Throw exception
                throw new BindException(result);
            }

            // Save the object
            this.scopeRepository.save(object);

            // Return the object
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

        // Return bad request
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Deletes a scope.
     * 
     * @param id The scope id.
     * @return The deleted scope.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Scope> delete(@PathVariable("id") String id) {
        // Delete the scope by it's id
        this.scopeRepository.deleteById(id);

        // Return ok
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
