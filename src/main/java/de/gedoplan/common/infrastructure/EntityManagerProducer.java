package de.gedoplan.common.infrastructure;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Produces for a default entity manager.
 * 
 * @author dw
 */
@ApplicationScoped
public class EntityManagerProducer {
  @PersistenceContext
  @Produces
  EntityManager entityManager;
}
