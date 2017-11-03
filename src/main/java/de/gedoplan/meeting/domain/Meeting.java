package de.gedoplan.meeting.domain;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.common.domain.Capacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.meeting.domain.attribute.MeetingName;
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
public class Meeting extends GeneratedLongIdEntity {

  @NotNull
  @Valid
  private MeetingName name;

  @NotNull
  @Valid
  private ZonedInterval interval;

  @NotNull
  @Valid
  private Capacity capacity;

  @ManyToMany
  @Setter(AccessLevel.NONE)
  private Set<Person> participants;

  @ManyToOne
  private Room room;

  Meeting(MeetingName name, Capacity capacity, ZonedInterval interval) {
    this.name = name;
    this.interval = interval;
    this.capacity = capacity;

    this.participants = new HashSet<>();
  }

}
