package de.gedoplan.dummy.domain;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;

import javax.inject.Inject;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * Dummy entity showing service injections.
 *
 * @author dw
 */
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

  public Dummy(String name) {
    this.name = name;
  }

  protected Dummy() {
  }

  @Override
  public String toString() {
    return super.toString() + " R#" + this.requestScopedService.getInstanceNumber() + " A#"
        + this.applicationScopedService.getInstanceCreated();
  }

}
