package de.gedoplan.dummy.presentation;

import de.gedoplan.common.service.ApplicationVersionService;
import de.gedoplan.dummy.entity.Dummy;
import de.gedoplan.dummy.persistence.DummyRepository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import lombok.Getter;

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
