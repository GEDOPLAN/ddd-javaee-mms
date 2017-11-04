package de.gedoplan.dummy.adapter;

import de.gedoplan.common.application.ApplicationVersionService;
import de.gedoplan.dummy.domain.Dummy;
import de.gedoplan.dummy.domain.DummyRepository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import lombok.Getter;

/**
 * Adapter for JSF (=managed bean).
 *
 * @author dw
 */
@Model
public class DummyPresenter {
  @Inject
  DummyRepository dummyRepository;

  @Getter
  private List<Dummy> dummies;

  @PostConstruct
  void postConstruct() {
    this.dummies = this.dummyRepository.findAll();
  }

  @Inject
  ApplicationVersionService applicationVersionService;

  public String getApplicationCoordinates() {
    return this.applicationVersionService.getGav();
  }

  public String getApplicationName() {
    return this.applicationVersionService.getName();
  }

}
