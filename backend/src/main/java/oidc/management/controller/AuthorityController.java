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
     */
    @PostMapping
    public ResponseEntity<Authority> save(@RequestBody Authority object) {
        // Remove the id
        object.setId(null);

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
     * @throws IOException
     */
    @PatchMapping("{id}")
    public ResponseEntity<Authority> update(@PathVariable("id") String id, HttpServletRequest request) throws IOException {
            
        // Get the optional holder
        Optional<Authority> optional = this.authorityRepository.findById(id);

        // Verify if the holder contains a value
        if ( optional.isPresent() ) {

            // Get the authority
            Authority authority = optional.get();

            // Update the authority
            authority = mapper.readerForUpdating(authority).readValue(request.getReader());

            // Save the authority
            this.authorityRepository.save(authority);

            // Return the authority
            return new ResponseEntity<>(authority, HttpStatus.OK);
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
