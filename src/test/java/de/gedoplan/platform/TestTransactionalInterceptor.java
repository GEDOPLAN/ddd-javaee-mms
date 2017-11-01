package de.gedoplan.platform;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;

/**
 * Interceptor class for binding {@link Transactional @Transactional}.
 *
 * This interceptor controls transactions as entity manager local transactions.
 * This works in combination with {@link TestEntityManagerProducer} only
 * because all injection poins must receive the same entity manager within the
 * same request (=thread).
 *
 * @author dw
 *
 */
@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION + 1)
public class TestTransactionalInterceptor implements Serializable {
  @Inject
  EntityManager entityManager;

  @Inject
  Log log;

  @AroundInvoke
  public Object manageTx(InvocationContext invocationContext) throws Exception {
    EntityTransaction transaction = this.entityManager.getTransaction();
    if (transaction.isActive()) {
      return invocationContext.proceed();
    }

    try {
      if (this.log.isTraceEnabled()) {
        this.log.trace("TX begin");
      }
      transaction.begin();
      Object result = invocationContext.proceed();
      if (this.log.isTraceEnabled()) {
        this.log.trace("TX commit");
      }
      transaction.commit();
      return result;
    } catch (Exception exception) {
      try {
        if (this.log.isTraceEnabled()) {
          this.log.trace("TX rollback");
        }
        transaction.rollback();
      } catch (Throwable throwable) {
      }
      throw exception;
    }
  }
}
