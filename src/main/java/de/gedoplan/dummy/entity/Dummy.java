package de.gedoplan.dummy.entity;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.dummy.service.ApplicationScopedService;
import de.gedoplan.dummy.service.DependentScopedService;
import de.gedoplan.dummy.service.RequestScopedService;

import javax.inject.Inject;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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
  }

  protected Dummy() {
  }

  @Override
  public String toString() {
    return super.toString() + " D#" + this.dependentScopedService.getInstanceNumber() + " R#" + this.requestScopedService.getInstanceNumber() + " A#"
        + this.applicationScopedService.getInstanceCreated();
  }

}
