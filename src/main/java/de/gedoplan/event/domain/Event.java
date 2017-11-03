package de.gedoplan.event.domain;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.event.domain.attribute.EventName;
import de.gedoplan.person.domain.Person;
import de.gedoplan.room.domain.Room;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends GeneratedLongIdEntity {

  @NotNull
  @Valid
  private EventName name;

  @NotNull
  @Valid
  private ZonedInterval interval;

  @NotNull
  @Valid
  private SeatCapacity capacity;

  @ManyToMany
  @Setter(AccessLevel.NONE)
  private Set<Person> participants;

  @ManyToOne
  private Room room;

  public Event(EventName name, SeatCapacity capacity, ZonedInterval interval) {
    this.name = name;
    this.interval = interval;
    this.capacity = capacity;

    this.participants = new HashSet<>();
  }

}
