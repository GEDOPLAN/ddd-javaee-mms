package de.gedoplan.platform;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.logging.Log;

@ApplicationScoped
public class TestTransactionService {
  @Inject
  EntityManager entityManager;

  @Inject
  Log log;

  public boolean begin() {
    EntityTransaction transaction = this.entityManager.getTransaction();
    if (transaction.isActive()) {
      return false;
    }

    if (this.log.isTraceEnabled()) {
      this.log.trace("TX begin");
    }
    transaction.begin();
    return true;
  }

  public void commit() {
    if (this.log.isTraceEnabled()) {
      this.log.trace("TX commit");
    }
    this.entityManager.getTransaction().commit();
  }

  public void rollback() {
    if (this.log.isTraceEnabled()) {
      this.log.trace("TX rollback");
    }
    this.entityManager.getTransaction().rollback();
  }
}
