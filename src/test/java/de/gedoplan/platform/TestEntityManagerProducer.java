package de.gedoplan.platform;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.logging.Log;

@RequestScoped
@Alternative
@Priority(1)
public class TestEntityManagerProducer {
  @Inject
  EntityManagerFactory entityManagerFactory;

  @Inject
  Log log;

  @Produces
  @RequestScoped
  public EntityManager createEntityManager() {
    EntityManager entityManager = this.entityManagerFactory.createEntityManager();

    if (this.log.isTraceEnabled()) {
      this.log.trace("Produced " + entityManager);
    }

    return entityManager;
  }

  void closeEntityManager(@Disposes EntityManager entityManager) {
    if (entityManager.isOpen()) {
      if (this.log.isTraceEnabled()) {
        this.log.trace("Close " + entityManager);
      }

      entityManager.close();
    }
  }
}
