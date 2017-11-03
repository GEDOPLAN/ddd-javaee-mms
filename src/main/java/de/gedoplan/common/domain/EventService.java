package de.gedoplan.common.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@ApplicationScoped
public class EventService {
  @Inject
  Event<Object> eventSource;

  public void fire(Object event) {
    this.eventSource.fire(event);
  }
}
