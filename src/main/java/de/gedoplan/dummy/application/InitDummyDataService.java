package de.gedoplan.dummy.application;

import de.gedoplan.dummy.domain.Dummy;
import de.gedoplan.dummy.domain.DummyRepository;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test data creator.
 *
 * This is just for conveniance and easy execution of the demo application in a Java EE server.
 *
 * @author dw
 */
@ApplicationScoped
public class InitDummyDataService implements Serializable {
  @Inject
  DummyRepository dummyRepository;

  private static Log log = LogFactory.getLog(InitDummyDataService.class);

  @Transactional
  void createDemoData(@Observes @Initialized(ApplicationScoped.class) ServletContext event) {
    try {
      if (this.dummyRepository.countAll() == 0) {
        for (int i = 1; i <= 10; ++i) {
          this.dummyRepository.merge(new Dummy(String.format("Dummy %03d", i)));
        }
      }
    } catch (Exception e) {
      log.warn("Cannot create test data", e);
    }
  }
}
