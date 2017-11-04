package de.gedoplan.platform;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Transactional;

/**
 * Interceptor class for binding {@link Transactional @Transactional}.
 *
 * This interceptor controls transactions as entity manager local transactions.
 * This works in combination with {@link TestEntityManagerProducer} only
 * because all injection points must receive the same entity manager within the
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
  TestTransactionService transactionService;

  @AroundInvoke
  public Object manageTx(InvocationContext invocationContext) throws Exception {
    boolean begunNewTx = this.transactionService.begin();
    if (!begunNewTx) {
      return invocationContext.proceed();
    }

    try {
      Object result = invocationContext.proceed();
      this.transactionService.commit();
      return result;
    } catch (Exception exception) {
      try {
        this.transactionService.rollback();
      } catch (Throwable throwable) {
      }
      throw exception;
    }
  }
}
