package de.gedoplan.dummy.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.commons.logging.Log;

@Dependent
public class DependentScopedService {

  private static int nextNumber = 1;

  private int instanceNumber;

  @Inject
  Log log;

  public int getInstanceNumber() {
    return this.instanceNumber;
  }

  @PostConstruct
  public void init() {
    this.instanceNumber = nextNumber;
    ++nextNumber;

    this.log.debug("created: " + this.instanceNumber);
  }

  @PreDestroy
  public void cleanup() {
    this.log.debug("destroy: " + this.instanceNumber);
  }

}
