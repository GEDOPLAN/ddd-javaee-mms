package de.gedoplan.meeting.domain;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.common.domain.attribute.Capacity;
import de.gedoplan.common.domain.attribute.ZonedInterval;
import de.gedoplan.meeting.domain.attribute.MeetingName;
import de.gedoplan.person.domain.Person;
import de.gedoplan.person.domain.PersonRepository;
import de.gedoplan.person.domain.attribute.EMail;
import de.gedoplan.person.domain.attribute.FirstName;
import de.gedoplan.person.domain.attribute.LastName;
import de.gedoplan.room.domain.Room;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Domain entity for meetings.
 * 
 * @author dw
 */
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
  @Setter(AccessLevel.NONE)
  private Room room;

  public void changeRoom(Room room) {

    if (this.room != null) {
      if (room != null && this.room.equals(room)) {
        return;
      }

      this.room.removeOccupancy(this.interval);
    }

    this.room = room;

    if (this.room != null) {
      this.room.addOccupancy(this.interval, this.name.getValue());
    }
  }

  Meeting(MeetingName name, Capacity capacity, ZonedInterval interval) {
    this.name = name;
    this.interval = interval;
    this.capacity = capacity;

    this.participants = new HashSet<>();
  }

  @Inject
  @Transient
  PersonRepository personRepository;

  public void registerParticipant(LastName lastName, FirstName firstName, EMail eMail) {
    Person person = this.personRepository.findByEMail(eMail);
    if (person == null) {
      person = new Person(lastName, firstName, eMail);
      this.personRepository.persist(person);
    }

    this.participants.add(person);
  }

}
