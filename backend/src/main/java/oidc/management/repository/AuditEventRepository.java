package oidc.management.repository;

import oidc.management.model.AuditEvent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository for persisting authentication audit events
 *
 * @param <T> An implementation of AuditEvent
 * @author Matías Hermosilla
 * @since 02-05-2022
 * @see {@link PagingAndSortingRepository}
 * @see {@link AuditEvent}
 */
@NoRepositoryBean
public interface AuditEventRepository<T extends AuditEvent> extends PagingAndSortingRepository<T, String>, CrudRepository<T, String> {

    public AuditEvent.AuditEventBuilder entityBuilder();

    public List<T> findAll();

}
