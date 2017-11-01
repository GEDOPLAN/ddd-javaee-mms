package de.gedoplan.dummy.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedLongIdEntity;
import de.gedoplan.dummy.service.ApplicationScopedService;
import de.gedoplan.dummy.service.DependentScopedService;
import de.gedoplan.dummy.service.RequestScopedService;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.inject.Inject;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Dummy extends GeneratedLongIdEntity {
  @NotNull
  @Size(min = 1)
  private String name;

  @Inject
  @Transient
  ApplicationScopedService applicationScopedService;

  @Inject
  @Transient
  RequestScopedService requestScopedService;

  @Inject
  @Transient
  DependentScopedService dependentScopedService;

  public Dummy(String name) {
    this.name = name;
    injectFields();
  }

  protected Dummy() {
  }

  @PostLoad
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private void injectFields() {
    BeanManager beanManager = CDI.current().getBeanManager();

    CreationalContext creationalContext = beanManager.createCreationalContext(null);

    AnnotatedType annotatedType = beanManager.createAnnotatedType(this.getClass());
    InjectionTarget injectionTarget = beanManager.createInjectionTarget(annotatedType);
    injectionTarget.inject(this, creationalContext);
  }

  @Override
  public String toString() {
    return super.toString() + " D#" + this.dependentScopedService.getInstanceNumber() + " R#" + this.requestScopedService.getInstanceNumber() + " A#"
        + this.applicationScopedService.getInstanceCreated();
  }

}
