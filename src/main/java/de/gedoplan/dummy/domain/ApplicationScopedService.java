package de.gedoplan.dummy.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;

@ApplicationScoped
public class ApplicationScopedService {
  private String instanceCreated;

  @Inject
  Log log;

  public String getInstanceCreated() {
    return this.instanceCreated;
  }

  @PostConstruct
  public void init() {
    this.instanceCreated = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    this.log.debug("created: " + this.instanceCreated);
  }

  @PreDestroy
  public void cleanup() {
    this.log.debug("destroy: " + this.instanceCreated);
  }
}
