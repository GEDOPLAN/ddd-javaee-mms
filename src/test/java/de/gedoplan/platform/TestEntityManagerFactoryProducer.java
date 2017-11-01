package de.gedoplan.platform;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
@Alternative
@Priority(1)
public class TestEntityManagerFactoryProducer {
  EntityManagerFactory entityManagerFactory;

  @PostConstruct
  void createEntityManagerFactory() {
    this.entityManagerFactory = Persistence.createEntityManagerFactory("conference-se");
  }

  @PreDestroy
  void closeEntityManagerFactory() {
    this.entityManagerFactory.close();
  }

  @Produces
  @ApplicationScoped
  EntityManagerFactory getEntityManagerFactory() {
    return this.entityManagerFactory;
  }
}
